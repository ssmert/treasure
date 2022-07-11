package com.jakers.mustneed.core.auth.api.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 요청 데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotNull
    @NotBlank
    @ApiModelProperty(notes = "이메일 아이디", required = true, example = "test@test.com")
    private String emailId;

    @ApiModelProperty(notes = "비밀번호", required = true, example = "asdf1234!")
    private String pw;
}

