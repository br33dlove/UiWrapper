package com.davidcryer.uiwrapperlibraryexample.common;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private final ResourceRepository repository;
    private final List<ResourceWrap> resourceWraps;
    private boolean allResourcesFetched = false;

    public ResourceManager(ResourceRepository repository) {
        this.repository = repository;
        resourceWraps = new ArrayList<>(repository.resourceCount());
    }

    public ResourceWrap get(final int id) {
        ResourceWrap wrap = resourceWraps.get(id);
        if (wrap == null) {
            wrap = new ResourceWrap(repository.getResource(id));
            resourceWraps.add(id, wrap);
        }
        return wrap;
    }

    public List<ResourceWrap> getAll() {
        if (!allResourcesFetched) {
            for (int i = 0; i < repository.resourceCount(); i++) {
                if (resourceWraps.get(i) == null) {
                    resourceWraps.add(i, new ResourceWrap(repository.getResource(i)));
                }
            }
            allResourcesFetched = true;
        }
        return resourceWraps;
    }

    public ResourceWrap create(final ResourceSubmission submission) throws BadResourceSubmissionException {
        final ResourceWrap w = new ResourceWrap(repository.createResource(submission));
        resourceWraps.add(w);
        return w;
    }
}
