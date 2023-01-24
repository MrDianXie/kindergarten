package com.dq408.kindergarten.utils.exception.tokenexption;

/**
 * 所给的token查不到相应用户
 * @author XIE_HRZGZ
 */
public class TokenUserIsAbsent extends RuntimeException{

    public TokenUserIsAbsent() {
    }

    public TokenUserIsAbsent(String message) {
        super(message);
    }
}
