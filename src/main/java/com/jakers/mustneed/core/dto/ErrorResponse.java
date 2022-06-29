package com.jakers.mustneed.core.dto;

import com.jakers.mustneed.core.enums.Errors;
import com.jakers.mustneed.core.exception.BaseRuntimeException;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 오류 응답
 */
@Getter
@Builder
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final int code;
    private final String message;

    /**
     * 응답 엔티티로 변환
     * @param e 예외
     * @return 응답 엔티티
     */
    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseRuntimeException e) {
        return ResponseEntity
            .status(e.getErrorStatus())
            .body(ErrorResponse.builder()
                .status(e.getErrorStatus())
                .error(HttpStatus.valueOf(e.getErrorStatus()).name())
                .code(e.getErrorCode())
                .message(e.getErrorMessage())
                .build());
    }

    /**
     * 응답 엔티티로 변환
     * @param httpStatus http 상태
     * @param errors 예외 enum
     * @return 응답 엔티티
     */
    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus httpStatus,
        Errors errors) {
        return ResponseEntity
            .status(httpStatus.value())
            .body(ErrorResponse.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .code(errors.getCode())
                .message(errors.getMessage())
                .build());
    }
}