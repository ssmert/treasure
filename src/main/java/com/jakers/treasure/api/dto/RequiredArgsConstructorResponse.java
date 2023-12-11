package com.jakers.treasure.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "RequiredArgsConstructor 응답")
@RequiredArgsConstructor(staticName = "of")
public class RequiredArgsConstructorResponse {

    @Schema(description = "값", example = "999")
    private final int pInt;

}