package com.jakers.mustneed.core.utils;

import com.google.common.base.Strings;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 요청 데이터 유틸
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

    /**
     * 현재 컨텍스트 요청 데이터 반환
     *
     * @return 요청 데이터
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 요청데이터의 IP 반환
     *
     * @param request 요청
     * @return IP
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            // 웹로직
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (Strings.isNullOrEmpty(ip)) {
            try {
                ip = Inet4Address.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = request.getRemoteAddr();
            }
        }

        return ip;
    }
}
