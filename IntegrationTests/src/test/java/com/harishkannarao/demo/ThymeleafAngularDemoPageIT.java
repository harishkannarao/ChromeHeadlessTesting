package com.harishkannarao.demo;

import com.harishkannarao.demo.page_objects.ThymeleafAngularDemoPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ThymeleafAngularDemoPageIT extends AbstractBaseIT {

    @Test
    public void shouldPerformAdditionWithDefaultValues() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        ThymeleafAngularDemoPage thymeleafAngularDemoPage = new ThymeleafAngularDemoPage(webDriver, testProperties);
        thymeleafAngularDemoPage.navigate();
        thymeleafAngularDemoPage.assertToBeOnCorrectPage();

        thymeleafAngularDemoPage.assertFirstOperandToBe("4");
        thymeleafAngularDemoPage.assertSecondOperandToBe("2");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("6");
    }

    @Test
    public void shouldPerformSubtractionWithDefaultValues() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        ThymeleafAngularDemoPage thymeleafAngularDemoPage = new ThymeleafAngularDemoPage(webDriver, testProperties);
        thymeleafAngularDemoPage.navigate();
        thymeleafAngularDemoPage.assertToBeOnCorrectPage();

        thymeleafAngularDemoPage.assertFirstOperandToBe("4");
        thymeleafAngularDemoPage.assertSecondOperandToBe("2");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("2");
    }

    @Test
    public void shouldPerformAddition() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        ThymeleafAngularDemoPage thymeleafAngularDemoPage = new ThymeleafAngularDemoPage(webDriver, testProperties);
        thymeleafAngularDemoPage.navigate();
        thymeleafAngularDemoPage.assertToBeOnCorrectPage();

        thymeleafAngularDemoPage.setFirstOperandAs("1");
        thymeleafAngularDemoPage.setSecondOperandAs("2");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("3");

        thymeleafAngularDemoPage.setFirstOperandAs("");
        thymeleafAngularDemoPage.setSecondOperandAs("2");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("2");

        thymeleafAngularDemoPage.setFirstOperandAs("3");
        thymeleafAngularDemoPage.setSecondOperandAs("");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("3");

        thymeleafAngularDemoPage.setFirstOperandAs("2");
        thymeleafAngularDemoPage.setSecondOperandAs("abcd");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("NaN");

        thymeleafAngularDemoPage.setFirstOperandAs("abcd");
        thymeleafAngularDemoPage.setSecondOperandAs("3");
        thymeleafAngularDemoPage.clickAddButton();
        thymeleafAngularDemoPage.assertResultToBe("NaN");
    }

    @Test
    public void shouldPerformSubtraction() throws Exception {
        WebDriver webDriver = webDriverFactory.newWebDriver();

        ThymeleafAngularDemoPage thymeleafAngularDemoPage = new ThymeleafAngularDemoPage(webDriver, testProperties);
        thymeleafAngularDemoPage.navigate();
        thymeleafAngularDemoPage.assertToBeOnCorrectPage();

        thymeleafAngularDemoPage.setFirstOperandAs("1");
        thymeleafAngularDemoPage.setSecondOperandAs("2");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("-1");

        thymeleafAngularDemoPage.setFirstOperandAs("");
        thymeleafAngularDemoPage.setSecondOperandAs("2");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("-2");

        thymeleafAngularDemoPage.setFirstOperandAs("3");
        thymeleafAngularDemoPage.setSecondOperandAs("");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("3");

        thymeleafAngularDemoPage.setFirstOperandAs("2");
        thymeleafAngularDemoPage.setSecondOperandAs("abcd");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("NaN");

        thymeleafAngularDemoPage.setFirstOperandAs("abcd");
        thymeleafAngularDemoPage.setSecondOperandAs("3");
        thymeleafAngularDemoPage.clickSubtractButton();
        thymeleafAngularDemoPage.assertResultToBe("NaN");
    }
}
