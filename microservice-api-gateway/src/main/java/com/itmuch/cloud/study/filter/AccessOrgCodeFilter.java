package com.itmuch.cloud.study.filter;

import com.itmuch.cloud.study.enums.ZuulFilterTypeEnums;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

/**
 *  校验请求的机构号参数
 *
 * @author mengka
 * @date 2017/07/15.
 */
@Slf4j
public class AccessOrgCodeFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return ZuulFilterTypeEnums.PRE.getName();
    }

    @Override
    public int filterOrder() {
        return 50001;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest().getRequestURI().matches("/user/.*");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        //机构号非空校验
        Object orgCode = request.getParameter("orgCode");
        if(orgCode==null){
            log.error("机构号参数缺失");
            ctx.setSendZuulResponse(false);//zuul不对该请求进行路由
            ctx.setResponseStatusCode(401);
            return null;
        }

        //机构号有效性校验
        if(!"123456".equals(orgCode)){
            log.error("机构号校验失败");
            ctx.setSendZuulResponse(false);//zuul不对该请求进行路由
            ctx.setResponseStatusCode(500);
            return null;
        }
        return null;
    }
}
