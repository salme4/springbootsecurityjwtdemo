package com.salme4.springbootsecurityjwtdemo.domain.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String nickname, String password){
        super(nickname, password);
    }

    public String getNickname(){
        return (String) super.getPrincipal();
    }

    public String getUserPassword(){
        return (String) super.getCredentials();
    }
}
