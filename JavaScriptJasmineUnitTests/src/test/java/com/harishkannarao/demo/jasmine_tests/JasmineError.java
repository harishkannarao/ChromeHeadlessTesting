package com.harishkannarao.demo.jasmine_tests;

public class JasmineError {
    private final String specDescription;
    private final String stackTrace;

    public JasmineError(String specDescription, String stackTrace) {
        this.specDescription = specDescription;
        this.stackTrace = stackTrace;
    }

    public String getFailureDescription() {
        return "Spec Description: " + specDescription + "\n" + "Stack Trace: " + stackTrace;
    }
}
