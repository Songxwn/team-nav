package com.tuituidan.teamnav.exception;

import com.tuituidan.teamnav.util.StringExtUtils;

import ch.qos.logback.classic.spi.EventArgUtil;

/**
 * ResourceReadException.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2021/3/6
 */
public class ResourceWriteException extends RuntimeException {

    private static final long serialVersionUID = 143422262865091780L;

    /**
     * ResourceReadException.
     *
     * @param message message
     * @param args    args
     */
    public ResourceWriteException(String message, Object... args) {
        super(StringExtUtils.format(message, args), EventArgUtil.extractThrowable(args));
    }
}
