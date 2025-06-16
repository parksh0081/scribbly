package com.example.scribbly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.scribbly.dto.UserDTO;
import com.example.scribbly.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
public class UsersController {
	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "users/signup";	// signup.html
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute UserDTO userDTO) {
		userService.register(userDTO);
		return "redirect:/login"; // 가입 후 로그인 페이지로 이동
	}
}
