package com.jakers.mustneed.account.application;

import com.jakers.mustneed.account.converter.AccountConverter;
import com.jakers.mustneed.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetrieveAccountService {

    private final AccountService accountService;
    private final AccountConverter accountConverter;

    public Account findByEmailId(String emailId) {
        return accountService.findByEmailId(emailId).orElseThrow();
    }
}
