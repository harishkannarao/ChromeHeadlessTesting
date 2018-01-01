package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CustomHeaderPage extends AbstractBasePage {
    public CustomHeaderPage(WebDriver webDriver, TestProperties testProperties) {
        super(webDriver, testProperties);
    }

    public void navigate() {
        navigateToUrl(testProperties.getTestBaseUrl() + CUSTOM_HEADER_DISPLAY_PAGE);
    }

    public void assertIsOnCorrectPage() {
        assertThat(getTitle(), equalTo("Custom Header display page"));
    }

    public void assertCustomHeaderValue(String value) {
        assertThat(findElementById("customHeader").getText(), is(value));
    }
}
