package com.harishkannarao.demo.page_objects;

import com.harishkannarao.demo.properties.TestProperties;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ThymeleafAngularDemoPage extends AbstractBasePage {
    public ThymeleafAngularDemoPage(WebDriver webDriver, TestProperties testProperties) {
        super(webDriver, testProperties);
    }

    public void navigate() {
        navigateToUrl(testProperties.getTestBaseUrl() + "/thymeleafAngularDemo");
    }

    public void assertToBeOnCorrectPage() {
        assertThat(findElementById("qa-thymeleaf-angular-page-id").isEnabled(), is(true));
    }

    public void setFirstOperandAs(String value) {
        setInputById("first", value);
    }

    public void setSecondOperandAs(String value) {
        setInputById("second", value);
    }

    public void clickAddButton() {
        clickElementById("addBtn");
    }

    public void assertResultToBe(String expectedValue) {
        String actualValue = getElementTextById("result");
        assertThat(actualValue, is(expectedValue));
    }

    public void clickSubtractButton() {
        clickElementById("subtractBtn");
    }

    public void assertFirstOperandToBe(String expectedValue) {
        String actualValue = getInputElementValueById("first");
        assertThat(actualValue, is(expectedValue));
    }

    public void assertSecondOperandToBe(String expectedValue) {
        String actualValue = getInputElementValueById("second");
        assertThat(actualValue, is(expectedValue));
    }
}
