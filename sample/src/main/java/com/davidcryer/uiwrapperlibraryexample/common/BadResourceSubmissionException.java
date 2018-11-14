package com.davidcryer.uiwrapperlibraryexample.common;

public class BadResourceSubmissionException extends Exception {
    private static final long serialVersionUID = 3884711165371619308L;

    BadResourceSubmissionException(String message) {
        super(message);
    }
}
