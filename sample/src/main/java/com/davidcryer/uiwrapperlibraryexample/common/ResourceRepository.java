package com.davidcryer.uiwrapperlibraryexample.common;

public class ResourceRepository {
    private Resource resource;

    public Resource create() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }
}
