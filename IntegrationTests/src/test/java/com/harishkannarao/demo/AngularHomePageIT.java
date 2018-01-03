package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.AngularAppHomePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class AngularHomePageIT extends AbstractBaseIT {

    @Test
    public void shouldPerformAddition() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        AngularAppHomePage angularAppHomePage = new AngularAppHomePage(webDriver, testProperties);
        angularAppHomePage.navigate();
        angularAppHomePage.assertHomePageId();

        angularAppHomePage.setFirstOperandAs("1");
        angularAppHomePage.setSecondOperandAs("2");
        angularAppHomePage.clickAddButton();
        angularAppHomePage.assertResultToBe("3");

        angularAppHomePage.setFirstOperandAs("");
        angularAppHomePage.setSecondOperandAs("2");
        angularAppHomePage.clickAddButton();
        angularAppHomePage.assertResultToBe("2");

        angularAppHomePage.setFirstOperandAs("3");
        angularAppHomePage.setSecondOperandAs("");
        angularAppHomePage.clickAddButton();
        angularAppHomePage.assertResultToBe("3");

        angularAppHomePage.setFirstOperandAs("2");
        angularAppHomePage.setSecondOperandAs("abcd");
        angularAppHomePage.clickAddButton();
        angularAppHomePage.assertResultToBe("NaN");

        angularAppHomePage.setFirstOperandAs("abcd");
        angularAppHomePage.setSecondOperandAs("3");
        angularAppHomePage.clickAddButton();
        angularAppHomePage.assertResultToBe("NaN");
    }

    @Test
    public void shouldPerformSubtraction() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        AngularAppHomePage angularAppHomePage = new AngularAppHomePage(webDriver, testProperties);
        angularAppHomePage.navigate();
        angularAppHomePage.assertHomePageId();

        angularAppHomePage.setFirstOperandAs("1");
        angularAppHomePage.setSecondOperandAs("2");
        angularAppHomePage.clickSubtractButton();
        angularAppHomePage.assertResultToBe("-1");

        angularAppHomePage.setFirstOperandAs("");
        angularAppHomePage.setSecondOperandAs("2");
        angularAppHomePage.clickSubtractButton();
        angularAppHomePage.assertResultToBe("-2");

        angularAppHomePage.setFirstOperandAs("3");
        angularAppHomePage.setSecondOperandAs("");
        angularAppHomePage.clickSubtractButton();
        angularAppHomePage.assertResultToBe("3");

        angularAppHomePage.setFirstOperandAs("2");
        angularAppHomePage.setSecondOperandAs("abcd");
        angularAppHomePage.clickSubtractButton();
        angularAppHomePage.assertResultToBe("NaN");

        angularAppHomePage.setFirstOperandAs("abcd");
        angularAppHomePage.setSecondOperandAs("3");
        angularAppHomePage.clickSubtractButton();
        angularAppHomePage.assertResultToBe("NaN");
    }
}
