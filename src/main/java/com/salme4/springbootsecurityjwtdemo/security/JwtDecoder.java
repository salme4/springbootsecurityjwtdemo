package com.salme4.springbootsecurityjwtdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtDecoder {

    private static final Logger logger = LoggerFactory.getLogger(JwtDecoder.class);

    public AccountContext decodeJwt(String token){
        DecodedJWT decodedJWT = isValidToken(token).orElseThrow(()-> new InvalidJwtException("유효한 토큰이 아닙니다."));
        String user_nickname = decodedJWT.getClaim("USER_NICKNAME").asString();
        String user_role = decodedJWT.getClaim("USER_ROLE").asString();
        return new AccountContext(user_nickname, "1234", user_role);
    }

    private Optional<DecodedJWT> isValidToken(String token){
         DecodedJWT jwt = null;
         try{
             Algorithm algorithm = Algorithm.HMAC256("jwttest");
             JWTVerifier verifier = JWT.require(algorithm).build();
             jwt = verifier.verify(token);
         } catch (Exception e){
             logger.error(e.getMessage());
         }

         return Optional.ofNullable(jwt);
    }
}
