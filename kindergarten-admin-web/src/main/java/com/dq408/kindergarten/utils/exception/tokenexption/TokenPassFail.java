package com.dq408.kindergarten.utils.exception.tokenexption;

/**
 * 无效token抛出异常
 * @author XIE_HRZGZ
 */
public class TokenPassFail extends RuntimeException{
    public TokenPassFail() {
    }

    public TokenPassFail(String message) {
        super(message);
    }
}
