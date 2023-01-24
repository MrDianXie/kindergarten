package com.dq408.kindergarten.utils.exception.tokenexption;

/**
 * 无token抛出异常
 * @author XIE_HRZGZ
 */
public class TokenIsNull extends RuntimeException{

    public TokenIsNull() {
    }

    public TokenIsNull(String message) {
        super(message);
    }
}
