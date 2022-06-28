package com.jakers.mustneed.core.config.converter;

import com.jakers.mustneed.core.enums.YesOrNo;
import org.springframework.core.convert.converter.Converter;

/**
 * 파라메터 <-> enum 변환
 */
public class YesOrNoEnumConverter implements Converter<String, YesOrNo> {

    @Override
    public YesOrNo convert(String value) {
        return YesOrNo.codeOf(value.toUpperCase()).orElse(null);
    }
}
