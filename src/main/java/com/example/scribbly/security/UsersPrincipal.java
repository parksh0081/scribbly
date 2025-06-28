package com.example.scribbly.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.scribbly.entity.Users;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsersPrincipal implements UserDetails {

	private final Users users;
	@Override
	public String toString() {
	    return "UsersPrincipal";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자 권한을 spring security 형식으로 리턴
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + users.getRole()));
	}

	@Override
	public String getPassword() {
		// DB에  저장된 비밀번호 반환
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// 로그인 아이디 반환
		return users.getUsername();
	}
	
	// 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명(비밀번호) 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

}
