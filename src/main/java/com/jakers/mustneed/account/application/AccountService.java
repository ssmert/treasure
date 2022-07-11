package com.jakers.mustneed.account.application;

import com.jakers.mustneed.account.domain.Account;
import com.jakers.mustneed.account.repository.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 계정 서비스
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> findByEmailId(String emailId) {
        return accountRepository.findByEmailId(emailId);
    }
}
