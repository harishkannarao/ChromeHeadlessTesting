package com.harishkannarao.demo.test_common.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class WebDriverFactory {

    private static final Logger LOGGER = Logger.getLogger(WebDriverFactory.class.getName());
    private static final List<WebDriver> WEB_DRIVERS = new ArrayList<>();

    private final Proxy proxy;
    private final ChromeDriverService chromeDriverService = createChromeDriverService();

    public WebDriverFactory() {
        this.proxy = null;
    }

    public WebDriverFactory(Proxy proxy) {
        this.proxy = proxy;
    }

    public WebDriver newWebDriver() {
        startChromeDriverService();
        WebDriver webDriver = new ChromeDriver(chromeDriverService, getDefaultDesiredCapabilities());
        WEB_DRIVERS.add(webDriver);
        return webDriver;
    }

    public void closeAllWebDrivers() {
        WEB_DRIVERS.forEach(WebDriver::close);
        WEB_DRIVERS.forEach(webDriver -> {
            try {
                webDriver.quit();
            } catch (Exception e) {
                LOGGER.warning("Exception while closing WebDriver: " + e.getMessage());
            }
        });
        WEB_DRIVERS.clear();
        stopChromeDriverService();
    }

    public void takeScreenShots(String displayName) {
        IntStream.range(0, WEB_DRIVERS.size())
                .forEach(index -> {
                    String filename = displayName + "_" + index;
                    WebDriverScreenShotUtil.takeScreenShot(WEB_DRIVERS.get(index), filename);
                });
    }

    private void startChromeDriverService() {
        if (!chromeDriverService.isRunning()) {
            try {
                chromeDriverService.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void stopChromeDriverService() {
        if (chromeDriverService.isRunning()) {
            chromeDriverService.stop();
        }
    }

    private DesiredCapabilities getDefaultDesiredCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        List<String> arguments = new ArrayList<>();
        arguments.add("--allow-insecure-localhost");
        arguments.add("--start-maximized");
        arguments.add("--disable-gpu");
        arguments.add("--no-sandbox");
        boolean isChromeHeadlessOn = Boolean.parseBoolean(System.getProperty("chromeHeadless", "false"));
        if (isChromeHeadlessOn) {
            arguments.add("--headless");
        }
        chromeOptions.addArguments(arguments);
        Optional<String> chromeBinary = Optional.ofNullable(System.getProperty("chromeBinary"));
        chromeBinary.ifPresent(chromeOptions::setBinary);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        if (proxy != null) {
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        }

        return capabilities;
    }

    private ChromeDriverService createChromeDriverService() {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        Optional<String> chromeDriverBinary = Optional.ofNullable(System.getProperty("chromeDriverBinary"));
        chromeDriverBinary.ifPresent(path -> builder.usingDriverExecutable(new File(path)));

        return builder
                .usingAnyFreePort()
                .build();
    }

}
