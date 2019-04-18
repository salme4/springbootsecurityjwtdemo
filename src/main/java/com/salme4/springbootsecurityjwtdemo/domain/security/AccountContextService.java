package com.salme4.springbootsecurityjwtdemo.domain.security;

import com.salme4.springbootsecurityjwtdemo.domain.Account;
import com.salme4.springbootsecurityjwtdemo.domain.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AccountContextService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public AccountContextService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByIdNAndNickname(nickname).orElseThrow(() -> new NoSuchElementException("계정 없어요."));

        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account){
        return AccountContext.fromAccountModel(account);
    }
}
