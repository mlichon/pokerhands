package com.maciej.lichon.poker.domain.exceptions;

/**
 *
 * @author mlichon
 */
public class HandContentException extends Exception {

    public HandContentException(String message) {
        super(message);
    }

    public HandContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandContentException(Throwable cause) {
        super(cause);
    }

    public HandContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
