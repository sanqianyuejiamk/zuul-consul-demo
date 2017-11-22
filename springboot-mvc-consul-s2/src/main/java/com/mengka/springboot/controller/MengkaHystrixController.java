package com.mengka.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.mengka.springboot.hystrix.MengkaHystrixService;
import com.mengka.springboot.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author huangyy
 * @description
 * @data 2017/02/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/app/v1/hystrix")
public class MengkaHystrixController {

    @Autowired
    private MengkaHystrixService mengkaHystrixService;

    @RequestMapping("/t_fallback")
    public String fallback(Map<String, Object> model, Long id){
        log.info("test Hystrix fallback API id = {}",id);

        String result = mengkaHystrixService.alwaysFailsMethod();
        return result;
    }

    @RequestMapping("/t_secondFallback")
    public String secondFallback(Map<String, Object> model, Long id){
        log.info("test Hystrix secondFallback API id = {}",id);

        String result = mengkaHystrixService.alwaysFailsDownTheChainMethod();
        return result;
    }

    @RequestMapping("/t_timeout")
    public String longRunning(Map<String, Object> model, Long id){
        log.info("test Hystrix timeout API id = {}",id);

        String result = mengkaHystrixService.longRunningMethod();
        return result;
    }

    @RequestMapping("/t_call")
    public String call(Map<String, Object> model, Long id){
        log.info("test Hystrix fallback API id = {}",id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","menkga");
        jsonObject.put("time", TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        return jsonObject.toString();
    }
}
