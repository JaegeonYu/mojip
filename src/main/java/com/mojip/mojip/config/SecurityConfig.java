package com.mojip.mojip.config;

import jakarta.servlet.FilterChain;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 모든 설정 자기가
public class SecurityConfig {

   @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(authorize ->
               authorize.requestMatchers("/", "/login", "/sign-up",
                       "/check-email", "check-email-token", "/email-login",
                       "/login-link", "/profile/*").permitAll()
                       .requestMatchers(HttpMethod.GET, "/profile/*").permitAll()
                       .anyRequest().authenticated());



       return http.build();
   }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

        return web -> web.ignoring()
                .requestMatchers("/node_modules/**","/templates/**")
                .requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations()));

    }
}
