package com.jakers.mustneed.core.auth.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    @ApiModelProperty(value = "인증 토큰")
    private String accessToken;

    @ApiModelProperty(value = "새로고침 토큰")
    private String refreshToken;
}

