package com.jakers.mustneed.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 여부 enum
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum YesOrNo {
    Yes("Y", "여"),
    No("N", "부");

    private String code;
    private String title;

    /**
     * 코드에 해당되는 열거형 상수 반환
     *
     * @param code 코드
     * @return enum
     */
    public static Optional<YesOrNo> codeOf(String code) {
        return Arrays.stream(values()).filter(val -> val.getCode().equals(code)).findFirst();
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getCode();
    }
}
