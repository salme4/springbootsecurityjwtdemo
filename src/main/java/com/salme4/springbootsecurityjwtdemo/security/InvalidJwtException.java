package com.salme4.springbootsecurityjwtdemo.security;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String msg) {
        super(msg);
    }
}
