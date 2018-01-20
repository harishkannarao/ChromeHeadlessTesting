package com.harishkannarao.demo.jasmine_tests;

import com.harishkannarao.demo.test_common.webdriver.WebDriverFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WebDriverTestConfigurations {

    @Bean
    public WebDriverFactory createWebDriverFactorySingleton() {
        return new WebDriverFactory();
    }
}
