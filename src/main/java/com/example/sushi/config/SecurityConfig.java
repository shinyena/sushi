package com.example.sushi.config;

import com.example.sushi.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final KakaoLoginService kakaoLoginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/sushi/**").hasAnyRole("USER", "ADMIN") // 사용자 화면
                .antMatchers("/admin/**").hasRole("ADMIN"); // 관리자 화면
        http.oauth2Login()
                .userInfoEndpoint()
                .userService(kakaoLoginService);

    }


}
