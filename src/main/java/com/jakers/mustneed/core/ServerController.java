package com.jakers.mustneed.core;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 서버 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "서버", description = "ServerController")
public class ServerController {

    @Operation(summary = "[공통] 서버 헬스체크", tags = "서버")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @GetMapping(value = "/check")
    public ResponseEntity<HttpStatus> checkHealth() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "[공통] 서버 시간 조회", tags = "서버")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @GetMapping(value = "/time")
    public ResponseEntity<String> retrieveTime() {
        return ResponseEntity.ok(
            ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
