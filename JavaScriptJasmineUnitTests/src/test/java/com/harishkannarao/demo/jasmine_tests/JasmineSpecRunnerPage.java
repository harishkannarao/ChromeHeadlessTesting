package com.harishkannarao.demo.jasmine_tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class JasmineSpecRunnerPage {

    private final WebDriver webDriver;

    public JasmineSpecRunnerPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void navigate() {
        webDriver.navigate().to("http://localhost:9090/JasmineSpecRunner.html");
    }

    public void assertNoFailures() {
        WebElement failures = webDriver.findElement(By.className("jasmine-failures"));

        List<WebElement> failedSpecs = failures.findElements(By.className("jasmine-failed"));

        List<String> failureDescriptions = failedSpecs.stream().map(this::toJasmineError).map(JasmineError::getFailureDescription).collect(Collectors.toList());

        if (!failureDescriptions.isEmpty()) {
            String printableMessage = failureDescriptions.stream().collect(Collectors.joining("\n"));
            Assert.fail("Jasmine Unit Test Failed !!! \n" + printableMessage);
        }

    }

    private JasmineError toJasmineError(WebElement element) {
        String specDescription = element.findElement(By.className("jasmine-description")).findElement(By.tagName("a")).getText();
        String stackTrace = element.findElement(By.className("jasmine-stack-trace")).getText();
        return new JasmineError(specDescription, stackTrace);
    }
}
