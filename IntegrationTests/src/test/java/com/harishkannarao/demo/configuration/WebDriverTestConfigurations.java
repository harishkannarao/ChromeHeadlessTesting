package com.harishkannarao.demo.configuration;

import com.harishkannarao.demo.test_common.webdriver.WebDriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.mitm.CertificateAndKeySource;
import net.lightbody.bmp.mitm.KeyStoreFileCertificateSource;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import org.openqa.selenium.Proxy;
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
    private BrowserMobProxy browserMobProxy;
    @Autowired
    private ChromeDriverService chromeDriverService;
    @Autowired
    private Proxy seleniumProxy;

    @Bean
    public BrowserMobProxy createBrowserMobProxySingleton() {
        CertificateAndKeySource existingCertificateSource =
                new KeyStoreFileCertificateSource("PKCS12", "/keystore.p12", "localhost", "my_keystore_password");

        // configure the MitmManager to use the same KeyStore source as the application
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(existingCertificateSource)
                .trustAllServers(true)
                .build();

        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.setMitmManager(mitmManager);
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
