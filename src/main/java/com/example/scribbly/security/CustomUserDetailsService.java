package com.example.scribbly.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.scribbly.entity.Users;
import com.example.scribbly.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UsersRepository usersRepository;
	
	// 로그인 시 username으로 사용자 정보 불러오는 함수
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("loadUserByUsername 호출됨: " + username);
		// username으로 DB에서 사용자 검색
		Users user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
				
		// Spring security에서 사용할 수 있게 UsersPrincipal로 포장
		return new UsersPrincipal(user);  // UsersPrincipal은 UserDetails 구현체
	}

}
