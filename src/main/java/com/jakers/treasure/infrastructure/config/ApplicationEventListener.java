package com.jakers.treasure.infrastructure.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class ApplicationEventListener {

    private final Environment environment;

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.name}")
    private String name;

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
        log.info("#    Project Name  : {}", name);
        log.info("#    StartUp Time : {}", now);
        log.info("#    Version       : {}", version);
        log.info("#    Profile       : {}", env);
        log.info("#####################################################################\n");
    }

    /**
     * 어플리케이션 종료 이벤트 리스너
     */
    @PreDestroy
    public void onShutDown() {
        String env = environment.getActiveProfiles()[0];
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("#######################      Server ShutDown     #######################");
        log.info("#    Project  Name  : {}", name);
        log.info("#    ShutDown Time : {}", now);
        log.info("#    Version       : {}", version);
        log.info("#    Profile       : {}", env);
        log.info("#####################################################################\n");
    }
}