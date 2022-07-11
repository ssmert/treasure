package com.jakers.mustneed.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Roles {
    USER("user", "사용자"),
    ADMIN("admin", "관리자");

    private String code;
    private String title;

    /**
     * 코드에 해당되는 enum 반환
     *
     * @param code 코드
     * @return enum
     */
    public static Optional<Roles> codeOf(String code) {
        return Arrays.stream(values()).filter(val -> val.getCode().equals(code)).findFirst();
    }

    /**
     * 코드 목록 반환
     *
     * @return 코드 목록
     */
    public static List<String> codes() {
        return Arrays.stream(values()).map(Roles::getCode).collect(Collectors.toList());
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getCode();
    }

}
