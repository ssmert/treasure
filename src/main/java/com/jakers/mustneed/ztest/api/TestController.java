package com.jakers.mustneed.ztest.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/access/all", method = RequestMethod.GET)
    public ResponseEntity<String> permitAll() {
        return ResponseEntity.ok("누구나 접근 가능");
    }

    @RequestMapping(value = "/access/login", method = RequestMethod.GET)
    public ResponseEntity<String> authenticated() {
        return ResponseEntity.ok("로그인 후 접근 가능\n");
    }

    @RequestMapping(value = "/access/user", method = RequestMethod.GET)
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("user 역할이 있는 사람 가능");
    }

    @RequestMapping(value = "/access/admin", method = RequestMethod.GET)
    public ResponseEntity<String> admin(@RequestHeader String Authorization) {
        log.debug(Authorization);
        return ResponseEntity.ok("admin 역할이 있는 사람 가능");
    }

}