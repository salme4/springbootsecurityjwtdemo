package com.salme4.springbootsecurityjwtdemo.security.tokens;

import com.salme4.springbootsecurityjwtdemo.dtos.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(FormLoginDto dto){
        this(dto.getNickname(), dto.getPassword());
    }

    private PreAuthorizationToken(String nickname, String password){
        super(nickname, password);
    }

    public String getNickname(){
        return (String) super.getPrincipal();
    }

    public String getUserPassword(){
        return (String) super.getCredentials();
    }
}
