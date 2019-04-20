package com.salme4.springbootsecurityjwtdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class JwtFactory {

    private final Logger logger = LoggerFactory.getLogger(JwtFactory.class);
    private static String signingKey = "jwttest";

    public String generateToken(AccountContext context){
        String token = null;

        try{
            token = JWT.create()
                    .withIssuer("davekim")
                    .withClaim("USER_NICKNAME", context.getAccount().getNickname())
                    .withClaim("USER_ROLE",  context.getAccount().getRole().getRoleName())
                    .sign(generateAlgorithm());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }


}
