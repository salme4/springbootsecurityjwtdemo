package com.salme4.springbootsecurityjwtdemo;

import com.salme4.springbootsecurityjwtdemo.domain.Account;
import com.salme4.springbootsecurityjwtdemo.domain.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner bootstrapTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account account = new Account();
            account.setPassword(passwordEncoder.encode("1234"));

            accountRepository.save(account);
        };
    }
}
