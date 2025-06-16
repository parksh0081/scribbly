package com.example.scribbly.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.scribbly.dto.UserDTO;
import com.example.scribbly.entity.Users;
import com.example.scribbly.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입
	public void register(UserDTO userDTO) {	
		System.out.println(userDTO);
		
		Users user = Users.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role("USER")
                .build();

        usersRepository.save(user);
	}
	
	
}
