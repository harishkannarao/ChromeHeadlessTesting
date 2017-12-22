package com.harishkannarao.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {
    private final String testBaseUrl;

    @Autowired
    public TestProperties(
            @Value("${test.baseUrl}") String testBaseUrl) {
        this.testBaseUrl = testBaseUrl;
    }

    public String getTestBaseUrl() {
        return testBaseUrl;
    }
}
