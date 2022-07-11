package com.jakers.mustneed.core.auth.application;

import com.jakers.mustneed.account.api.dto.AccountRequest;
import com.jakers.mustneed.account.application.ChangeAccountService;
import com.jakers.mustneed.account.application.RetrieveAccountService;
import com.jakers.mustneed.account.domain.Account;
import com.jakers.mustneed.core.auth.api.dto.SignInRequest;
import com.jakers.mustneed.core.auth.api.dto.SignInResponse;
import com.jakers.mustneed.core.auth.api.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 인증 서비스
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final KeycloakService keycloakService;
    private final ChangeAccountService changeAccountService;
    private final RetrieveAccountService retrieveAccountService;

    /**
     * 회원가입
     *
     * @param request 요청 데이터
     */
    public void signUp(SignUpRequest request) {
        String authId = keycloakService.signUp(request);
        changeAccountService.register(
            new AccountRequest(authId, request.getEmailId(), request.getPw()));
    }

    /**
     * 로그인
     *
     * @param request 요청 데이터
     * @return 로그인 응답데이터
     */
    public SignInResponse signIn(SignInRequest request) {
        Account account = retrieveAccountService.findByEmailId(request.getEmailId());
        return keycloakService.signIn(request);
    }

}
