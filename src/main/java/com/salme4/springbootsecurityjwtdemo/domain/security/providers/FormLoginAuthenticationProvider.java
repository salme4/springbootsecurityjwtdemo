package com.salme4.springbootsecurityjwtdemo.domain.security.providers;

import com.salme4.springbootsecurityjwtdemo.domain.Account;
import com.salme4.springbootsecurityjwtdemo.domain.AccountRepository;
import com.salme4.springbootsecurityjwtdemo.domain.security.AccountContext;
import com.salme4.springbootsecurityjwtdemo.domain.security.AccountContextService;
import com.salme4.springbootsecurityjwtdemo.domain.security.tokens.PostAuthorizationToken;
import com.salme4.springbootsecurityjwtdemo.domain.security.tokens.PreAuthorizationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    private final AccountContextService accountContextService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public FormLoginAuthenticationProvider(AccountContextService accountContextService, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountContextService = accountContextService;
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
        throw  new NoSuchElementException("아직은 인증 안돼");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account){
        //matches 원본이 첫 번째 파라메터로 와야한다.
        return passwordEncoder.matches(account.getPassword(), password);
    }
}
