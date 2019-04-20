package com.salme4.springbootsecurityjwtdemo.security.providers;

import com.salme4.springbootsecurityjwtdemo.domain.Account;
import com.salme4.springbootsecurityjwtdemo.domain.AccountRepository;
import com.salme4.springbootsecurityjwtdemo.security.AccountContext;
import com.salme4.springbootsecurityjwtdemo.security.AccountContextService;
import com.salme4.springbootsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import com.salme4.springbootsecurityjwtdemo.security.tokens.PreAuthorizationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public FormLoginAuthenticationProvider(AccountContextService accountContextService, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //인증 처리
        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String nickname = token.getNickname();
        String password = token.getUserPassword();

        Account account = accountRepository.findByNickname(nickname).orElseThrow(() -> new NoSuchElementException("account Not Found!!"));
        if(isCorrectPassword(password, account)){
            //return Post authentication token.
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }
        //인증이 왜 안됐는지 처리
        throw  new NoSuchElementException("아직은 인증 안돼");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account){
        //matches 원본이 첫 번째 파라메터로 와야한다.
        return passwordEncoder.matches(password, account.getPassword());
    }
}
