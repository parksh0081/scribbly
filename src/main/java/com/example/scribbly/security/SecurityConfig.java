package com.example.scribbly.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final LoginSuccessHandler loginSuccessHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // CSRF 비활성화 (테스트용)
        .authorizeHttpRequests()
            .requestMatchers("/", "/error", "/error/**", // 하위 경로도 같이 추가
            		"/logo/scribbly.png" ,"/users/checkIdJson", "/scribbly", "/scribbly/login", "/scribbly/blog", 
            		"/scribbly/signup", "/css/**", "/js/**", "/images/**").permitAll() // 비로그인 허용
            .anyRequest().authenticated() // 그 외엔 로그인 필요
        .and()
        .formLogin()
            .loginPage("/scribbly/login") // 커스텀 로그인 페이지
            .loginProcessingUrl("/scribbly/login") // 로그인 처리 URL (POST)
            .successHandler(loginSuccessHandler)
            .failureHandler(failureHandler())
            .permitAll()
        .and()
        .logout()
        	.logoutUrl("/scribbly/logout")
            .logoutSuccessUrl("/scribbly")
            .permitAll()
            
	    .and()
	    	.exceptionHandling()
	        .accessDeniedPage("/scribbly/login?error=true");
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
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(daoAuthenticationProvider()); // 핵심 등록
        return auth.build();
    }
    
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
        	System.out.println("로그인 실패 핸들러 호출됨");
            exception.printStackTrace(); // 콘솔에 에러 출력
            response.sendRedirect("/scribbly/login?error=true");
        };
    }

}
