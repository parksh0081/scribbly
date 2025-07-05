package com.example.scribbly.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.scribbly.entity.Users;
import com.example.scribbly.repository.UsersRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final UsersRepository usersRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 로그인한 사용자 정보
		UsersPrincipal principal = (UsersPrincipal) authentication.getPrincipal();
		Users user = principal.getUsers();
		
		// 현재 시각으로 last_login 업데이트
		user.setLast_login(new Date());
		usersRepository.save(user);  // 갱신
		
		// 로그인 성공 후 리디렉션
		response.sendRedirect("/scribbly/" + user.getUsername());
	}

}
