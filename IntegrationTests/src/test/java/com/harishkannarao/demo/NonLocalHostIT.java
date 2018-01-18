package com.harishkannarao.demo;

import com.harishkannarao.demo.constants.CookieDomain;
import com.harishkannarao.demo.page_objects.CustomHeaderPage;
import com.harishkannarao.demo.page_objects.HomePage;
import com.harishkannarao.demo.page_objects.ViewCookiePage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

public class NonLocalHostIT extends AbstractBaseIT {

    @Before
    public void setUp() throws Exception {
        assumeThat("Skipping this test as system property 'runNonLocalHostTests' is not set to 'true'. To run these tests, set -DrunNonLocalHostTests=true", runNonLocalHostTests(), equalTo(true));
    }

    @Test
    public void should_createAndSendSecureCookie_toTheServer() throws Exception {
        String expectedCookieValue = "my-test-cookie";

        WebDriver webDriver = webDriverFactory.newWebDriver();
        String exampleDotComBaseUrl = testProperties.getExampleDotComBaseUrl();

        HomePage homePage = new HomePage(webDriver, testProperties);
        homePage.navigate(exampleDotComBaseUrl);

        assertThat("Please import the latest self signed certificate in OS and mark it as trusted", homePage.getSslErrorsFromBrowserLogs(), empty());

        homePage.addMyCookieWithValue(CookieDomain.EXAMPLE_DOT_COM, expectedCookieValue);

        ViewCookiePage viewCookiePage = new ViewCookiePage(webDriver, testProperties);
        viewCookiePage.navigate(exampleDotComBaseUrl);
        assertThat(viewCookiePage.getCookieValue(), equalTo(expectedCookieValue));
    }

    @Test
    public void should_displayCustomHeader() throws Exception {
        String customHeaderValue = "myCustomHeaderValue";
        String customHeaderKey = "my-custom-header";
        browserMobProxy.addHeader(customHeaderKey, customHeaderValue);

        WebDriver webDriver = webDriverFactory.newWebDriver();
        String exampleDotOrgBaseUrl = testProperties.getExampleDotOrgBaseUrl();
        CustomHeaderPage customHeaderPage = new CustomHeaderPage(webDriver, testProperties);

        customHeaderPage.navigate(exampleDotOrgBaseUrl);
        assertThat("Please import the latest self signed certificate in OS and mark it as trusted", customHeaderPage.getSslErrorsFromBrowserLogs(), empty());
        customHeaderPage.assertIsOnCorrectPage();
        customHeaderPage.assertCustomHeaderValue(customHeaderValue);
    }

}
