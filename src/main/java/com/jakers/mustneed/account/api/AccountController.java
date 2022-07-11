package com.jakers.mustneed.account.api;

import com.jakers.mustneed.account.api.dto.AccountRequest;
import com.jakers.mustneed.account.api.dto.AccountResponse;
import com.jakers.mustneed.account.application.ChangeAccountService;
import com.jakers.mustneed.account.application.RetrieveAccountService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 계정 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
@Tag(name = "계정", description = "AccountController")
public class AccountController {

    private final RetrieveAccountService retrieveAccountService;
    private final ChangeAccountService changeAccountService;

    @Operation(summary = "목록 조회", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AccountResponse.class)})
    @GetMapping(value = "")
    public ResponseEntity<Page<AccountResponse>> retrieveAccountList(
        @PageableDefault(size = 30, sort = "regAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "단건 조회", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AccountResponse.class)})
    @GetMapping(value = "/{account-id}")
    public ResponseEntity<AccountResponse> retrieveAccount(
        @PathVariable("account-id") Long accountId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "추가", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")})
    @PostMapping(value = "")
    public ResponseEntity<Void> registerAccount(
        @RequestBody @Valid AccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "전체 수정", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No Content")})
    @PutMapping(value = "/{account-id}")
    public ResponseEntity<Void> editAccount(@PathVariable("account-id") Long accountId,
        @RequestBody @Valid AccountRequest request) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "일부 수정", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No Content")})
    @PatchMapping(value = "/{account-id}")
    public ResponseEntity<Void> updateAccount(@PathVariable("account-id") Long accountId,
        @RequestBody @Valid AccountRequest request) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "삭제", tags = "계정")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No Content")})
    @DeleteMapping(value = "/{account-id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity.noContent().build();
    }

}