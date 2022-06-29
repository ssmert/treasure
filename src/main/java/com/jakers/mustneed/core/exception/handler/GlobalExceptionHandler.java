package com.jakers.mustneed.core.exception.handler;

import com.jakers.mustneed.core.dto.ErrorResponse;
import com.jakers.mustneed.core.enums.Errors;
import com.jakers.mustneed.core.exception.BaseRuntimeException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역 예외 핸들러
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 잘못된 인자 예외
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> BadRequestExceptionHandler(HttpServletResponse response,
        BindException e) {
        log.error("잘못된 인자 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, Errors.BAD_REQUEST);
    }

    /**
     * 인증 예외
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> UnauthorizedExceptionHandler(HttpServletResponse response,
        AuthenticationException e) {
        log.error("인증 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(HttpStatus.UNAUTHORIZED, Errors.UNAUTHORIZED);
    }

    /**
     * 런타임 예외는 {@link Errors}에 정의된 예외 코드와 메시지로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ErrorResponse> BaseRunTimeExceptionHandler(HttpServletResponse response,
        BaseRuntimeException e) {
        log.error("런타임 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(e);
    }

    /**
     * 미확인 예외
     *
     * @param response HTTP 서블릿 응답데이터
     * @param t        최상위 예외
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> ThrowableHandler(HttpServletResponse response,
        Throwable t) {
        log.error("요청을 처리하던 중 예상하지 못한 오류가 발생했습니다.", t);
        return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
            Errors.INTERNAL_SERVER_ERROR);
    }
}
