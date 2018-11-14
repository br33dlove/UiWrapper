package com.davidcryer.uiwrapperlibraryexample.common;

import java.util.List;

public class ResourceRepository {
    private final List<Resource> resources;

    public ResourceRepository(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public Resource getResource(final int id) {
        if (id < 0 || id >= resources.size()) {
            return null;
        }
        return resources.get(id);
    }

    public Resource createResource(final ResourceSubmission submission) throws BadResourceSubmissionException {
        if (submission.getValue().isEmpty()) {
            throw new BadResourceSubmissionException("Enter something :-)");
        }
        final Resource r = new Resource(submission.getValue());
        resources.add(r);
        return r;
    }

    public int resourceCount() {
        return resources.size();
    }
}
