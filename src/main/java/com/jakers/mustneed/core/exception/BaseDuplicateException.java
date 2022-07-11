package com.jakers.mustneed.core.exception;

import com.jakers.mustneed.core.enums.Errors;
import org.springframework.http.HttpStatus;

/**
 * 데이터 중복 예외
 */
public class BaseDuplicateException extends BaseRuntimeException {

    public BaseDuplicateException(String message) {
        super(message);
    }

    public BaseDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDuplicateException(Errors errors) {
        super(errors, HttpStatus.CONFLICT);
    }
}
