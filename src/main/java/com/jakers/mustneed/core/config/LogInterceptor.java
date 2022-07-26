package com.jakers.mustneed.core.config;

import com.google.common.collect.Iterables;
import com.jakers.mustneed.core.utils.RequestUtil;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * HTTP 거래 로그 인터셉터
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    // 미출력 URI 목록
    private final List<String> skipUrls = Arrays.asList("/error", "/swagger-ui",
        "/swagger-resources", "/v3/api-docs");

    /**
     * 거래 시작 핸들러
     *
     * @param request  요청 데이터
     * @param response 응답 데이터
     * @param handler  핸들러
     * @return 항상 참
     * @throws Exception 예외
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        // 미출력 URI 체크
        boolean isSkip = Iterables.any(skipUrls, request.getRequestURI()::startsWith);

        if (!isSkip) {
            // 접속 IP를 구한다.
            String ip = RequestUtil.getIp(request);
            String userId = "Anonymous";

            log.info("==================          START         ==================");
            log.info("{} {} ", request.getServletPath(), request.getMethod());
            log.info("Access IP [{}] / USER [{}]", ip, userId);
        }

        return true;
    }

    /**
     * 거래 종료 핸들러
     *
     * @param request   요청 데이터
     * @param response  응답 데이터
     * @param handler   핸들러
     * @param exception 예외
     * @throws Exception 예외
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception exception) throws Exception {
        // 미출력 URI 체크
        boolean isSkip = Iterables.any(skipUrls, request.getRequestURI()::startsWith);

        if (!isSkip) {
            log.info("{} {} ", request.getServletPath(), request.getMethod());
            log.info("==================           END          ==================");
        }
    }
}
