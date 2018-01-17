package com.harishkannarao.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {
    private final String testBaseUrl;
    private final String exampleDotComBaseUrl;
    private final String exampleDotOrgBaseUrl;

    @Autowired
    public TestProperties(
            @Value("${test.baseUrl}") String testBaseUrl,
            @Value("${test.exampleDotComBaseUrl}") String exampleDotComBaseUrl,
            @Value("${test.exampleDotOrgBaseUrl}") String exampleDotOrgBaseUrl
    ) {
        this.testBaseUrl = testBaseUrl;
        this.exampleDotComBaseUrl = exampleDotComBaseUrl;
        this.exampleDotOrgBaseUrl = exampleDotOrgBaseUrl;
    }

    public String getTestBaseUrl() {
        return testBaseUrl;
    }

    public String getExampleDotComBaseUrl() {
        return exampleDotComBaseUrl;
    }

    public String getExampleDotOrgBaseUrl() {
        return exampleDotOrgBaseUrl;
    }
}
