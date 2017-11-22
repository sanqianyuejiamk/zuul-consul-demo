package com.itmuch.cloud.study.enums;

import org.apache.commons.lang.StringUtils;

/**
 *  zuul四种不同生命周期的过滤器类型
 *
 * @author mengka
 * @date 2017/07/15.
 */
public enum ZuulFilterTypeEnums {

    PRE("pre","在请求被路由之前调用"),
    ROUTING("route","在路由请求时候被调用"),
    POST("post","在routing和error过滤器之后被调用"),
    ERROR("error","处理请求时发生错误时被调用");

    private String name;

    private String desc;

    ZuulFilterTypeEnums(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    public static ZuulFilterTypeEnums valueOfType(String name){
        if(StringUtils.isBlank(name)){
            return null;
        }
        for(ZuulFilterTypeEnums tmpEnum:values()){
            if(tmpEnum.name.equals(name)){
                return tmpEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
