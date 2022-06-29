package com.jakers.mustneed.core.config.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 최상위 서브 응답
 */
@Getter
@Setter
public abstract class BaseSubResponse {

    // 최초 등록자
    protected String regId;
    // 최초 등록일시
    protected LocalDateTime regAt;
    // 최종 변경자
    protected String udtId;
    // 최종 변경일시
    protected LocalDateTime udtAt;
}
