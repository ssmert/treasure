package com.jakers.mustneed.core.exception;

import com.jakers.mustneed.core.enums.Errors;
import org.springframework.http.HttpStatus;

/**
 * 데이터 없음 예외
 */
public class BaseNotFoundException extends BaseRuntimeException {

    public BaseNotFoundException(String message) {
        super(message);
    }

    public BaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseNotFoundException(Errors errors) {
        super(errors, HttpStatus.NOT_FOUND);
    }
}
