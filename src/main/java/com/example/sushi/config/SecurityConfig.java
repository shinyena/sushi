package com.example.sushi.config;

import com.example.sushi.service.KakaoOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.WebappServiceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final KakaoOAuth2UserService kakaoOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/sushi/**").hasRole("USER")
                .antMatchers("/sushi/admin/**").hasRole("ADMIN");
        http.oauth2Login()
                .defaultSuccessUrl("/sushi/register")
                .userInfoEndpoint()
                .userService(kakaoOAuth2UserService);

    }


}
