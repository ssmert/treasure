package com.jakers.mustneed.account.converter;

import com.jakers.mustneed.account.api.dto.AccountResponse;
import com.jakers.mustneed.account.domain.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * DTO 변환기
 */
@Component
public class AccountConverter {

    public AccountResponse convert(Account account) {
        AccountResponse response = new AccountResponse();
        BeanUtils.copyProperties(account, response);
        return response;
    }
}
