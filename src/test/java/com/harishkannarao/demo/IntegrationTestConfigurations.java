package com.harishkannarao.demo;

import com.harishkannarao.demo.webdriver.WebDriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@TestConfiguration
public class IntegrationTestConfigurations {

    @Autowired
    private BrowserMobProxy browserMobProxy;
    @Autowired
    private ChromeDriverService chromeDriverService;
    @Autowired
    private Proxy seleniumProxy;

    @Bean
    public BrowserMobProxy createBrowserMobProxySingleton() {
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setTrustAllServers(true);
        browserMobProxy.start(0);
        Runtime.getRuntime().addShutdownHook(new Thread(browserMobProxy::stop));

        return browserMobProxy;
    }

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
    public Proxy createSeleniumProxy() {
        Proxy seleniumProxy = new Proxy();
        seleniumProxy.setProxyType(Proxy.ProxyType.MANUAL);

        String proxyStr = String.format("http://localhost:%d", browserMobProxy.getPort());
        seleniumProxy.setHttpProxy(proxyStr);
        seleniumProxy.setSslProxy(proxyStr);

        return seleniumProxy;
    }

    @Bean
    public WebDriverFactory createWebDriverFactorySingleton() {
        return new WebDriverFactory(seleniumProxy, chromeDriverService);
    }
}
