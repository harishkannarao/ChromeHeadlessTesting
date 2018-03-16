package com.harishkannarao.demo.jasmine_tests;

import com.harishkannarao.demo.jasmine_tests_application.JasmineUnitTestsApplication;
import com.harishkannarao.demo.test_common.webdriver.WebDriverFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {
                JasmineUnitTestsApplication.class,
                WebDriverTestConfigurations.class
        },
        webEnvironment = DEFINED_PORT
)
public class JasmineSpecRunnerIT {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @After
    public void tearDown() {
        webDriverFactory.closeAllWebDrivers();
    }

    @Test
    public void shouldRunJasmineSpecs() {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        JasmineSpecRunnerPage specRunnerPage = new JasmineSpecRunnerPage(webDriver);
        specRunnerPage.navigate();
        specRunnerPage.assertNoFailures();
    }
}
