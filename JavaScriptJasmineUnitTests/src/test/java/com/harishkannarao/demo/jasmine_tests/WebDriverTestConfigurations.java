package com.harishkannarao.demo.jasmine_tests;

import com.harishkannarao.demo.test_common.webdriver.WebDriverFactory;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@TestConfiguration
public class WebDriverTestConfigurations {

    @Autowired
    private ChromeDriverService chromeDriverService;

    @Bean
    public ChromeDriverService createChromeDriverServiceSingleton() {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        Optional<String> chromeDriverBinary = Optional.ofNullable(System.getProperty("chromeDriverBinary"));
        chromeDriverBinary.ifPresent(path -> builder.usingDriverExecutable(new File(path)));
        ChromeDriverService service = builder
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(service::stop));

        return service;
    }

    @Bean
    public WebDriverFactory createWebDriverFactorySingleton() {
        return new WebDriverFactory(chromeDriverService);
    }
}
