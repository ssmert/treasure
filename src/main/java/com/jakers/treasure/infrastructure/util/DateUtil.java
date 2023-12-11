package com.jakers.treasure.infrastructure.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 날짜 유틸
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    public static final String DATE_FORMAT_YMD = "yyyyMMdd";
    public static final String DATE_FORMAT_YMD_DASH = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMS = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_YMS_DASH = "yyyy-MM-dd HH:mm:ss";

}
