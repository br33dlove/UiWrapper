package com.davidcryer.uiwrapperlibraryexample.common;

public class Resource {
    private String value;

    Resource(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    void value(final String value) {
        this.value = value;
    }
}
