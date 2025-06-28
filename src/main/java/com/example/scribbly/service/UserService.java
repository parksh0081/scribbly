package com.example.scribbly.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scribbly.dao.UserDAO;
import com.example.scribbly.dto.UserDTO;
import com.example.scribbly.entity.Users;
import com.example.scribbly.repository.UsersRepository;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserDAO dao;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void testPasswordMatch() {
        String raw = "aaa";
        String hashed = "$2a$10$Xeg.b005QWJ.osRQ9Eh0CO31DPQjw37IN3ExSWgHtqvv2ZhZEmQ02";

        boolean matched = passwordEncoder.matches(raw, hashed);
        System.out.println("암호 비교 결과: " + matched);
    }
    
    // 회원가입
    @Transactional
    public void register(UserDTO userDTO) {
        Users user = Users.builder()
                .user_id(generateUserId())  // UUID 또는 커스텀 ID 생성 메서드
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .profile_image(userDTO.getProfile_image())
                .blog_title(userDTO.getBlog_title())
                .role(userDTO.getRole() != null ? userDTO.getRole() : "USER")
                .created_at(new Date())
                .build();

        usersRepository.save(user);
    }

    private String generateUserId() {
        // UUID 랜덤 생성 (실제로는 좀 더 안전하게 관리)
        return java.util.UUID.randomUUID().toString();
    }
    
    // 회원가입 아이디 존재 검사
    public boolean isExistId(String username) {
    	return dao.isExistId(username);
    }
    
    // 로그인
    public Optional<Users> login(String username) {
    	return usersRepository.findByUsername(username);
    }

}
