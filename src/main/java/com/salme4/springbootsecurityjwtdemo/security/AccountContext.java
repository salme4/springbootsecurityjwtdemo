package com.salme4.springbootsecurityjwtdemo.security;


import com.salme4.springbootsecurityjwtdemo.domain.Account;
import com.salme4.springbootsecurityjwtdemo.domain.UserRole;
import com.salme4.springbootsecurityjwtdemo.security.tokens.JwtPostProccessingToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {

    private Account account;

    public static AccountContext fromAccountModel(Account account){
        return new AccountContext(account, account.getNickname(), account.getPassword(), parseAuthorities(account.getRole()));
    }

    public AccountContext(String nickname, String password, String role) {
        super(nickname, password, parseAuthorities(role));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(String role){
        return parseAuthorities(UserRole.getRoleByName(role));
    }

    private AccountContext(Account account, String nickname, String password, Collection<? extends GrantedAuthority> authorities) {
        super(nickname, password, authorities);
        this.account = account;
    }

    public static AccountContext fromJwtPostToken(JwtPostProccessingToken token){
        return  new AccountContext(null, token.getNickName(), token.getPassword(), token.getAuthorities());
    }

    //정의한 Role 들을 SimpleGrantedAuthority 의 생성자에 전달하여 List 형태로 받는다.
    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role){
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public Account getAccount() {
        return account;
    }
}
