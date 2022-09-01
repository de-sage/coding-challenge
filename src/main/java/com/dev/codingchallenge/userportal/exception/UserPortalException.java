package com.dev.codingchallenge.userportal.exception;

public class UserPortalException  extends Exception {

    public UserPortalException() {
        super();
    }

    public UserPortalException(String message) {
        super(message);
    }

    public UserPortalException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPortalException(Throwable cause) {
        super(cause);
    }

    protected UserPortalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
