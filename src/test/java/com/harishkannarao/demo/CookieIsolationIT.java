package com.harishkannarao.demo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

public class CookieIsolationIT extends AbstractBaseIT {

    @Test
    public void should_isolateCookies_betweenMultipleWebDrivers() throws Exception {
        WebDriver firstWebDriver = webDriverFactory.newWebDriver();
        String expectedCookieValue = "my-test-cookie";
        String viewCookiePath = "/viewCookie";
        firstWebDriver.navigate().to(testProperties.getTestBaseUrl());
        Cookie localhostCookie = new Cookie.Builder("my-cookie", expectedCookieValue)
                .domain("localhost")
                .isHttpOnly(true)
                .isSecure(true)
                .build();
        firstWebDriver.manage().addCookie(localhostCookie);
        firstWebDriver.navigate().to(testProperties.getTestBaseUrl() + viewCookiePath);

        String cookieValueInFirstBrowser = firstWebDriver.findElement(By.id("cookieValue")).getText();
        assertThat(cookieValueInFirstBrowser, equalTo(expectedCookieValue));

        WebDriver secondWebDriver = webDriverFactory.newWebDriver();
        secondWebDriver.navigate().to(testProperties.getTestBaseUrl() + viewCookiePath);
        String cookieValueInSecondBrowser = secondWebDriver.findElement(By.id("cookieValue")).getText();
        assertThat(cookieValueInSecondBrowser, isEmptyString());
    }
}
