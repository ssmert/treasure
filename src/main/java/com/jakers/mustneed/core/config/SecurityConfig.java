package com.jakers.mustneed.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurity 설정
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Environment environment;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Swagger 접속 허용(로컬,개발,테스트)
        if (environment.acceptsProfiles(Profiles.of("local | dev | qa"))) {
            http.authorizeRequests()
                .antMatchers("/swagger-**/**", "/configuration/**", "/v3/api-docs").permitAll();
        }

        // CORS(Cross-Origin Resource Sharing : 타 도메인 간 자원 호출을 승인하거나 차단) 활성화
        http.cors();
        // CSRF(Cross Site Request Forgery : 사이트 간 요청 위조) 비활성화
        http.csrf().disable();
        // 인증 실패시 Redirect(302) 대신 401 Status만 리턴하도록 설정
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        // STATELESS : Security 인증 세션을 유지하지 않도록 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            // preflight(http method options) 요청 오류 방지
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // static resources
            .antMatchers("/css/**", "/js/**", "/resources/**", "/webjars/**").permitAll();

        // 접근 설정
        http.authorizeRequests()
            // 코어(ServerController)
            .antMatchers(HttpMethod.GET, "/check").permitAll()
            .antMatchers(HttpMethod.GET, "/time").permitAll();

        // TODO> TEST
        http.authorizeRequests().antMatchers("/**").permitAll();

        // 모든 요청 인증 확인
        http.authorizeRequests().anyRequest().authenticated();

        return http.build();
    }
}
