package com.itmuch.cloud.study.filter;

import com.itmuch.cloud.study.enums.ZuulFilterTypeEnums;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

/**
 *  校验请求的token参数
 *
 * @author mengka
 * @date 2017/07/15.
 */
@Slf4j
public class AccessFilter extends ZuulFilter {

    /**
     *  返回一个字符串代表过滤器的类型，在zuul中定义了四种不同的生命周期过滤器类型
     *  1）pre：在请求被路由之前调用；
     *  2）routing：在路由请求时候被调用；
     *  3）post：在routing和error过滤器之后被调用；
     *  4）error：处理请求时发生错误时被调用；
     *
     * @return
     */
    @Override
    public String filterType() {
        return ZuulFilterTypeEnums.PRE.getName();
    }

    /**
     *  通过int值来定义过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 50000;
    }

    /**
     *  返回一个boolean类型来判断该过滤器是否要执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest().getRequestURI().matches("/user/.*");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);//令zuul过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);//返回的错误码
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
