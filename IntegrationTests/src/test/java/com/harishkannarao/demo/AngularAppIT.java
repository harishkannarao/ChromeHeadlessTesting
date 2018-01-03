package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.AngularAppHomePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class AngularAppIT extends AbstractBaseIT {

    @Test
    public void shouldBootstrapAngularApp() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();
        AngularAppHomePage angularAppHomePage = new AngularAppHomePage(webDriver, testProperties);
        angularAppHomePage.navigate();
        angularAppHomePage.assertHomePageId();
    }
}
