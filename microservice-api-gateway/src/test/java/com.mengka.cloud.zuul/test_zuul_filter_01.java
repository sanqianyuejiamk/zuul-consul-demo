package com.mengka.cloud.zuul;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mengka
 * @date 2017/07/15.
 */
@Slf4j
public class test_zuul_filter_01 {

    /**
     * Response response = get("/lotto");
     * String body = response.getBody().asString();
     * String headerValue = response.getHeader("headerName");
     * String cookieValue = response.getCookie("cookieName");
     */
    @Test
    public void test_zuul_token_filter_01() {
        log.info("test_zuul_token_filter_01..");
        Response response = RestAssured.get("http://127.0.0.1:8050/user/2?orgCode=123456&accessToken=123456");
        assertEquals(200, response.getStatusCode());

        String result = response.getBody().asString();
        JSONObject jsonObject = JSON.parseObject(result);
        assertEquals("Jerry", jsonObject.getString("username"));
    }

    @Test
    public void test_zuul_token_filter_02() {
        log.info("test_zuul_token_filter_02..");
        Response response = RestAssured.get("http://127.0.0.1:8050/user/2");
        assertEquals(401, response.getStatusCode());
    }
}
