package com.harishkannarao.demo.constants;

public enum CookieDomain {
    LOCALHOST("localhost"),
    EXAMPLE_DOT_COM(".example.com");
    private final String value;

    CookieDomain(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
