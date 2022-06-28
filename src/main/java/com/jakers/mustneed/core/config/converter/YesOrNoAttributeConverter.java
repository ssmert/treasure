package com.jakers.mustneed.core.config.converter;

import com.jakers.mustneed.core.enums.YesOrNo;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * DB <-> enum 변환
 */
@Converter(autoApply = true)
public class YesOrNoAttributeConverter implements AttributeConverter<YesOrNo, String> {

    @Override
    public String convertToDatabaseColumn(YesOrNo attribute) {
        return attribute.getCode();
    }

    @Override
    public YesOrNo convertToEntityAttribute(String dbData) {
        return YesOrNo.codeOf(dbData).orElse(null);
    }
}
