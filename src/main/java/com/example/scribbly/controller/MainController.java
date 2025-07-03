package com.example.scribbly.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.scribbly.entity.Users;
import com.example.scribbly.security.UsersPrincipal;
import com.example.scribbly.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final UserService service;
	
	@GetMapping("/scribbly")
	public String main(Model model, @AuthenticationPrincipal UsersPrincipal principal) {
	    if (principal != null) {
	        Users user = principal.getUsers();
	        model.addAttribute("user", user);
	    }
	    // principal이 null이든 아니든 view는 보여줌
	    return "/main/scribbly";
	}
}
