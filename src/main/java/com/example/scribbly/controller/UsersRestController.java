package com.example.scribbly.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.scribbly.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsersRestController {
	
	private final UserService service;
	
	// @RequestBody로 JSON 형태로 전달된 데이터 받기
	@PostMapping("/users/checkIdJson")
	public ResponseEntity<Map<String, Object>> checkIdJson(@RequestBody Map<String, String> requestBody) {
		String username = requestBody.get("username");	// json에서 username 추출
		
		boolean result = service.isExistId(username);	// DAO에서 아이디 중복 여부 확인
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("result", result);	// 아이디 사용 가능 여부 반환
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);	// 성공 응답
	}
	
}
