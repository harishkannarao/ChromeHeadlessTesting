package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.WebDriver;

public class ViewCookiePage extends AbstractBasePage {

    public ViewCookiePage(WebDriver webDriver, TestProperties testProperties) {
        super(webDriver, testProperties);
    }

    public void navigate() {
        navigateToUrl(testProperties.getTestBaseUrl() + VIEW_COOKIE_PAGE);
    }

    public String getCookieValue() {
        return findElementById("cookieValue").getText();
    }
}
