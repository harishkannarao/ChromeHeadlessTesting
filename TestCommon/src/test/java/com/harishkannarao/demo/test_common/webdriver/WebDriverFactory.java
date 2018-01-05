package com.harishkannarao.demo.test_common.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class WebDriverFactory {

    private static final Logger LOGGER = Logger.getLogger(WebDriverFactory.class.getName());
    private static final List<WebDriver> WEB_DRIVERS = new ArrayList<>();

    private final Proxy proxy;
    private final ChromeDriverService chromeDriverService;

    public WebDriverFactory(Proxy proxy, ChromeDriverService chromeDriverService) {
        this.proxy = proxy;
        this.chromeDriverService = chromeDriverService;
    }

    public WebDriver newWebDriver() {
        WebDriver webDriver = createChromeWebDriver();
        WEB_DRIVERS.add(webDriver);
        return webDriver;
    }

    private WebDriver createChromeWebDriver() {
        return new ChromeDriver(chromeDriverService, getDefaultDesiredCapabilities());
    }

    public DesiredCapabilities getDefaultDesiredCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        List<String> arguments = new ArrayList<>();
        arguments.add("--allow-insecure-localhost");
        arguments.add("--start-maximized");
        arguments.add("--disable-gpu");
        arguments.add("--no-sandbox");
        Optional<String> chromeBinary = Optional.ofNullable(System.getProperty("chromeBinary"));
        chromeBinary.ifPresent(chromeOptions::setBinary);
        chromeOptions.addArguments(arguments);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        capabilities.setCapability(CapabilityType.PROXY, proxy);

        return capabilities;
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

    }

    public void takeScreenShots(String displayName) {
        IntStream.range(0, WEB_DRIVERS.size())
                .forEach(index -> {
                    String filename = displayName + "_" + index;
                    WebDriverScreenShotUtil.takeScreenShot(WEB_DRIVERS.get(index), filename);
                });
    }
}
