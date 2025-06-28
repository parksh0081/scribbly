package com.example.scribbly.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/scribbly/blog")
	public String blog(Model model, @AuthenticationPrincipal UsersPrincipal principal) {
	    if (principal == null) return "redirect:/scribbly/login?error=true";
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("Authorities: " + auth.getAuthorities());
	    System.out.println("Principal: " + auth.getPrincipal());
	    System.out.println("Is Authenticated: " + auth.isAuthenticated());
	    
	    Users user = principal.getUsers();
	    model.addAttribute("user", user);
	    return "/main/blog";
	}
	
}
