package com.jakers.mustneed.account.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 계정 요청 데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @ApiModelProperty(notes = "인증 아이디", required = true, example = "12345")
    private String authId;

    @ApiModelProperty(notes = "이메일 아이디", required = true, example = "test@test.com")
    private String emailId;

    @ApiModelProperty(notes = "비밀번호", required = true, example = "asdf1234!")
    private String pw;
}

