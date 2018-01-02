package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AbstractBasePage {
    protected static final String MY_COOKIE = "my-cookie";
    protected static final String VIEW_COOKIE_PAGE = "/viewCookie";
    protected static final String CUSTOM_HEADER_DISPLAY_PAGE = "/showCustomHeader";
    protected static final String COOKIE_DOMAIN = "localhost";

    protected final WebDriver webDriver;
    protected final TestProperties testProperties;

    public AbstractBasePage(WebDriver webDriver, TestProperties testProperties) {
        this.webDriver = webDriver;
        this.testProperties = testProperties;
    }

    public void navigateToUrl(String url) {
        webDriver.navigate().to(url);
    }

    protected void addCookie(String name, String value) {
        Cookie localhostCookie = new Cookie.Builder(name, value)
                .domain(COOKIE_DOMAIN)
                .isHttpOnly(true)
                .isSecure(true)
                .build();
        webDriver.manage().addCookie(localhostCookie);
    }

    protected WebElement findElementById(String id) {
        return webDriver.findElement(By.id(id));
    }

    protected String getTitle() {
        return webDriver.getTitle();
    }

    protected void waitForPresenceOfElementByClass(String className) {
        WebDriverWait explicitWait = new WebDriverWait(webDriver, 10);
        explicitWait.until(presenceOfElementLocated(By.className(className)));
    }
}
