package com.jakers.mustneed.core.config;

import com.jakers.mustneed.core.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * 키클락 설정
 */
@KeycloakConfiguration
@RequiredArgsConstructor
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private final Environment environment;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /**
     * ROLE_ 접두사 붙이지 않도록 처리
     *
     * @param auth 인증매니저 빌더
     * @throws Exception 예외
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

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

        // TODO> TEST
//        http.authorizeRequests().antMatchers("/**").permitAll();

        // 접근 설정
        http.authorizeRequests()
            // ServerController
            .antMatchers(HttpMethod.GET, "/check").permitAll()
            .antMatchers(HttpMethod.GET, "/time").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll();

        http.authorizeRequests()
            .antMatchers("/test/access/all").permitAll()
            .antMatchers("/test/access/login").hasAnyRole(Strings.join(Roles.codes(), ','))
            .antMatchers("/test/access/user").hasAnyRole(Roles.USER.getCode())
            .antMatchers("/test/access/admin").hasAnyRole(Roles.ADMIN.getCode());

        // 모든 요청 인증 확인
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}
