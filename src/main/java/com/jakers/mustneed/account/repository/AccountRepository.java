package com.jakers.mustneed.account.repository;


import com.jakers.mustneed.account.domain.Account;
import com.jakers.mustneed.core.repository.DomainRepository;
import java.util.Optional;

/**
 * 계정 도메인 레파지토리
 */
public interface AccountRepository extends DomainRepository<Account, Long>,
    AccountCustomRepository {

    Optional<Account> findByEmailId(String emailId);
}
