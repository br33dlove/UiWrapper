package com.davidc.uiwrapper;

final class ArgChecker {

    private ArgChecker() {

    }

    static void notNull(final Object o, final String varName) {
        if (o == null) {
            throw new IllegalArgumentException(defaultExceptionMessage(varName));
        }
    }

    private static String defaultExceptionMessage(final String varName) {
        return String.format("%1$s cannot be null", varName);
    }
}
