package com.example.scribbly.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // CSRF 비활성화 (테스트용)
        .authorizeHttpRequests()
            .requestMatchers("/", "/users/checkIdJson", "/scribbly", "/scribbly/login", "/scribbly/blog", 
            		"/scribbly/signup", "/css/**", "/js/**", "/images/**").permitAll() // 비로그인 허용
            .anyRequest().authenticated() // 그 외엔 로그인 필요
        .and()
        .formLogin()
            .loginPage("/scribbly/login") // 커스텀 로그인 페이지
            .permitAll()
        .and()
        .logout()
            .logoutSuccessUrl("/scribbly")
            .permitAll();

		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 방식
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

}
