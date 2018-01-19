package com.harishkannarao.demo;

import com.harishkannarao.demo.configuration.WebDriverTestConfigurations;
import com.harishkannarao.demo.properties.TestProperties;
import com.harishkannarao.demo.test_common.webdriver.WebDriverFactory;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {
                ChromeHeadlessTestingDemoApplication.class,
                WebDriverTestConfigurations.class
        },
        webEnvironment = DEFINED_PORT
)
@ActiveProfiles(profiles = {"default", "integration-test"})
public abstract class AbstractBaseIT {
    @Autowired
    protected WebDriverFactory webDriverFactory;
    @Autowired
    protected BrowserMobProxy browserMobProxy;
    @Autowired
    protected TestProperties testProperties;

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void finished(Description description) {
            super.finished(description);
            webDriverFactory.takeScreenShots(description.getDisplayName());
            webDriverFactory.closeAllWebDrivers();
            browserMobProxy.removeAllHeaders();
        }
    };

    protected boolean runNonLocalHostTests() {
        return Boolean.parseBoolean(System.getProperty("runNonLocalHostTests", "false"));
    }
}
