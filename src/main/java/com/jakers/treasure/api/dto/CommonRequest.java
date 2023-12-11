package com.jakers.treasure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jakers.treasure.infrastructure.util.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@Schema(description = "요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonRequest {

    @Schema(description = "값", required = true, example = "999")
    @Range(min = 1, max = 999, message = "1 ~ 999")
    private int pInt;

    @Schema(description = "일시", required = true, example = "20221225203011")
    @JsonFormat(pattern = DateUtil.DATE_FORMAT_YMS, shape = Shape.STRING)
    private LocalDateTime dt;
}