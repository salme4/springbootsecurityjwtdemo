package com.salme4.springbootsecurityjwtdemo.security.tokens;

import com.salme4.springbootsecurityjwtdemo.security.filters.handler.JwtAuthenticationFailureHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreProccessingToken extends UsernamePasswordAuthenticationToken {
    private JwtPreProccessingToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreProccessingToken(String token){
        this(token, token.length());
    }

}
