package com.zhu8fei.easytest.core.commons.exception;

/**
 * Created by zhu8fei on 2017/5/7.
 */
public class EasyTestException extends Exception {
    public EasyTestException() {
        super();
    }

    public EasyTestException(String message) {
        super(message);
    }

    public EasyTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyTestException(Throwable cause) {
        super(cause);
    }

    protected EasyTestException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
