package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.constants.CookieDomain;
import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {
    public HomePage(WebDriver webDriver, TestProperties testProperties) {
        super(webDriver, testProperties);
    }

    public void navigate() {
        navigate(testProperties.getTestBaseUrl());
    }

    public void navigate(String baseUrl) {
        navigateToUrl(baseUrl);
    }

    public void addMyCookieWithValue(String value) {
        addMyCookieWithValue(CookieDomain.LOCALHOST, value);
    }

    public void addMyCookieWithValue(CookieDomain cookieDomain, String value) {
        addCookie(cookieDomain, MY_COOKIE, value);
    }
}
