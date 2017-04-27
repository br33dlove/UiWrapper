package com.davidc.uiwrapper;

final class ArgChecker {

    static void notNull(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException(defaultExceptionMessage(o));
        }
    }

    private static String defaultExceptionMessage(final Object o) {
        return String.format("%1$s cannot be null", o.getClass().getSimpleName());
    }
}
