package com.itmuch.cloud.study.component;

import com.itmuch.cloud.study.filter.AccessOrgCodeFilter;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.monitoring.MonitoringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *  应用启动时，会调用CommandLineRunner的run()方法；
 *
 * @author mengka
 * @date 2017/07/15.
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunner run..");
        MonitoringHelper.initMocks();
        initJavaFilters();
    }

    private void initJavaFilters() {
        final FilterRegistry registry = FilterRegistry.instance();
        registry.put("accessOrgCodeFilter",new AccessOrgCodeFilter());
    }
}
