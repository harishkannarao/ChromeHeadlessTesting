package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.CustomHeaderPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CustomHeaderPageIT extends AbstractBaseIT {

    @Test
    public void shouldDisplayCustomHeader() throws Exception {
        String customHeaderValue = "myCustomHeaderValue";
        String customHeaderKey = "my-custom-header";
        browserMobProxy.addHeader(customHeaderKey, customHeaderValue);

        WebDriver webDriver = webDriverFactory.newWebDriver();
        CustomHeaderPage customHeaderPage = new CustomHeaderPage(webDriver, testProperties);

        customHeaderPage.navigate();
        customHeaderPage.assertIsOnCorrectPage();
        customHeaderPage.assertCustomHeaderValue(customHeaderValue);
    }

    @Test
    public void shouldDisplayNoCustomHeader_givenCustomHeaderIsNotPassed() throws Exception {
        String customHeaderKey = "my-custom-header";
        browserMobProxy.removeHeader(customHeaderKey);
        WebDriver webDriver = webDriverFactory.newWebDriver();

        CustomHeaderPage customHeaderPage = new CustomHeaderPage(webDriver, testProperties);

        customHeaderPage.navigate();
        customHeaderPage.assertIsOnCorrectPage();
        customHeaderPage.assertCustomHeaderValue("No Custom Header");
    }
}
