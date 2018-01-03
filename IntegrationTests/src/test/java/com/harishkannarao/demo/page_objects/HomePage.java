package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {
    public HomePage(WebDriver webDriver, TestProperties testProperties) {
        super(webDriver, testProperties);
    }

    public void navigate() {
        navigateToUrl(testProperties.getTestBaseUrl());
    }

    public void addMyCookieWithValue(String value) {
        addCookie(MY_COOKIE, value);
    }


}
