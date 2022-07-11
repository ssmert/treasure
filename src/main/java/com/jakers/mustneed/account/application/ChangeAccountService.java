package com.jakers.mustneed.account.application;


import com.jakers.mustneed.account.api.dto.AccountRequest;
import com.jakers.mustneed.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 변경 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ChangeAccountService {

    private final AccountService accountService;

    public void register(AccountRequest request) {
        Account account = Account.of(request.getAuthId(), request.getEmailId(), request.getPw());
        accountService.save(account);
    }
}
