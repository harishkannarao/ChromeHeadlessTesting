package com.harishkannarao.demo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SecureCookieIT extends AbstractBaseIT {

    @Test
    public void should_createAndSendSecureCookie_toTheServer() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();
        String expectedCookieValue = "my-test-cookie";
        String viewCookiePath = "/viewCookie";
        webDriver.navigate().to(testProperties.getTestBaseUrl());
        Cookie localhostCookie = new Cookie.Builder("my-cookie", expectedCookieValue)
                .domain("localhost")
                .isHttpOnly(true)
                .isSecure(true)
                .build();
        webDriver.manage().addCookie(localhostCookie);
        webDriver.navigate().to(testProperties.getTestBaseUrl() + viewCookiePath);

        String actualCookieValue = webDriver.findElement(By.id("cookieValue")).getText();
        assertThat(actualCookieValue, equalTo(expectedCookieValue));
    }
}
