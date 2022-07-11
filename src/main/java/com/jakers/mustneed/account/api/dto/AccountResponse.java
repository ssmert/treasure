package com.jakers.mustneed.account.api.dto;

import com.jakers.mustneed.core.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 계정 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse extends BaseResponse {

    private String authId;
    private String emailId;
}

