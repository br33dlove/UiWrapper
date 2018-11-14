package com.davidcryer.uiwrapperlibraryexample.common;

import java.util.ArrayList;
import java.util.List;

public class Collections {

    public static <I, O> List<O> mapByLoop(final List<I> in, final Function<I, O> mapping) {
        final List<O> out = new ArrayList<>();
        for (final I i : in) {
            out.add(mapping.apply(i));
        }
        return out;
    }

    public interface Function<I, O> {
        O apply(I i);
    }
}
