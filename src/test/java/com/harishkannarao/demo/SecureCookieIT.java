package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.HomePage;
import com.harishkannarao.demo.page_objects.ViewCookiePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SecureCookieIT extends AbstractBaseIT {

    @Test
    public void should_createAndSendSecureCookie_toTheServer() throws Exception {
        String expectedCookieValue = "my-test-cookie";

        WebDriver webDriver = webDriverFactory.newWebDriver();

        HomePage homePage = new HomePage(webDriver, testProperties);
        homePage.navigate();
        homePage.addMyCookieWithValue(expectedCookieValue);

        ViewCookiePage viewCookiePage = new ViewCookiePage(webDriver, testProperties);
        viewCookiePage.navigate();
        assertThat(viewCookiePage.getCookieValue(), equalTo(expectedCookieValue));
    }
}
