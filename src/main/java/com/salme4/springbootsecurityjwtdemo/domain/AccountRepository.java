package com.salme4.springbootsecurityjwtdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNickname(String nickname);

    Optional<Account> findBySocialId(Long socialId);
}
