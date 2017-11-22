package com.itmuch.cloud.study.config;

import com.itmuch.cloud.study.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mengka
 * @date 2017/07/15.
 */
@Configuration
public class AppConfig {

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
