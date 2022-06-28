package com.jakers.mustneed.core.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 어플리케이션 이벤트 리스너
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppEventListener {

    private final Environment environment;

    /**
     * 어플리케이션 시작 이벤트 리스너
     *
     * @param event 이벤트
     */
    @EventListener
    public void onStartUp(ApplicationReadyEvent event) {
        String env = environment.getActiveProfiles()[0];
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("#######################      Server StartUp     #######################");
        log.info("#    Start Up Time : {}", now);
        log.info("#    Profile       : {}", env);
        log.info("#####################################################################\n");
    }

    /**
     * 어플리케이션 종료 이벤트 리스너
     */
    @PreDestroy
    public void onShutDown() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("#######################      Server ShutDown     #######################");
        log.info("#    ShutDown Time : {}", now);
        log.info("#####################################################################\n");
    }
}