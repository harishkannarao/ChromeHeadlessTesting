package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.HomePage;
import com.harishkannarao.demo.page_objects.ViewCookiePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

public class MultipleBrowserCookieIsolationIT extends AbstractBaseIT {

    @Test
    public void should_isolateCookies_betweenMultipleWebDrivers() throws Exception {
        String expectedCookieValue = "my-test-cookie";

        WebDriver firstWebDriver = webDriverFactory.newWebDriver();
        WebDriver secondWebDriver = webDriverFactory.newWebDriver();

        HomePage homePageOnFirstBrowser = new HomePage(firstWebDriver, testProperties);
        homePageOnFirstBrowser.navigate();
        homePageOnFirstBrowser.addMyCookieWithValue(expectedCookieValue);

        ViewCookiePage viewCookiePageOnFirstBrowser = new ViewCookiePage(firstWebDriver, testProperties);
        viewCookiePageOnFirstBrowser.navigate();
        assertThat(viewCookiePageOnFirstBrowser.getCookieValue(), equalTo(expectedCookieValue));

        ViewCookiePage viewCookiePageOnSecondBrowser = new ViewCookiePage(secondWebDriver, testProperties);
        viewCookiePageOnSecondBrowser.navigate();
        assertThat(viewCookiePageOnSecondBrowser.getCookieValue(), isEmptyString());
    }
}
