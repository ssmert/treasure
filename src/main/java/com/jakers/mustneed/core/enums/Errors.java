package com.jakers.mustneed.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 예외 enum
 */
@Getter
@RequiredArgsConstructor
public enum Errors {
    UNAUTHORIZED(1000, "인증되지 않은 사용자입니다."),
    BAD_REQUEST(1001, "잘못된 요청입니다."),

    NOT_FOUND_USER(2001, "존재하지 않는 사용자입니다."),

    DUPLICATED_USER(3001, "동일한 사용자가 존재합니다."),

    INTERNAL_SERVER_ERROR(9999, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다.");

    private final int code;
    private final String message;
}
