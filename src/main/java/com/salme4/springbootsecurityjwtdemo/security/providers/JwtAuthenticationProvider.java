package com.salme4.springbootsecurityjwtdemo.security.providers;

import com.salme4.springbootsecurityjwtdemo.security.AccountContext;
import com.salme4.springbootsecurityjwtdemo.security.JwtDecoder;
import com.salme4.springbootsecurityjwtdemo.security.tokens.JwtPreProccessingToken;
import com.salme4.springbootsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        AccountContext context = jwtDecoder.decodeJwt(token);
        return PostAuthorizationToken.getTokenFromAccountContext(context);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtPreProccessingToken.class.isAssignableFrom(aClass);
    }
}
