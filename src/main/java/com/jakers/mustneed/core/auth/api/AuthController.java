package com.jakers.mustneed.core.auth.api;

import com.jakers.mustneed.core.auth.api.dto.SignInRequest;
import com.jakers.mustneed.core.auth.api.dto.SignInResponse;
import com.jakers.mustneed.core.auth.api.dto.SignUpRequest;
import com.jakers.mustneed.core.auth.application.AuthService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "인증", description = "AuthController")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", tags = "인증")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created")})
    @PostMapping(value = "/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
        authService.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "로그인", tags = "인증")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Ok")})
    @PostMapping(value = "/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest request) {
        SignInResponse response = authService.signIn(request);

        return ResponseEntity.ok(response);
    }

}