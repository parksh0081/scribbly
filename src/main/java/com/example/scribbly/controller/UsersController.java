package com.example.scribbly.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.scribbly.dto.UserDTO;
import com.example.scribbly.entity.Users;
import com.example.scribbly.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersController {
	
	private final UserService userService;
	
	
	@GetMapping("/scribbly/signup")
	public String signupForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "/users/signupForm";
	}
	
	// application.properties에서 경로 주입
	@Value("${project.upload.path1}")
    private String uploadPath;

    @PostMapping("/scribbly/signup")
    public String signup(@ModelAttribute UserDTO userDTO,
                         @RequestParam("profile") MultipartFile file) throws IOException {
    	
    	//profile_image
        if (!file.isEmpty()) {
            // 고유한 파일명 생성
            String uuid = UUID.randomUUID().toString();
            String originalName = file.getOriginalFilename();
            String newFileName = uuid + "_" + originalName;

            // 실제 저장할 경로
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File saveFile = new File(uploadPath, newFileName);
            file.transferTo(saveFile);

            // 웹에서 접근 가능한 경로로 저장
            userDTO.setProfile_image("/users/" + newFileName);
        }
        
        //create_at
    	userDTO.setCreated_at(new Date());
    	
    	//회원가입
        userService.register(userDTO);
        
        return "/users/signup";  
    }
    
    // 로그인
    @GetMapping("/scribbly/login")
    public String loginForm() {
    	return "/users/loginForm";
    }
    
    @PostMapping("/scribbly/login")
    public String login(HttpServletRequest request, Model model) {
		boolean result = false;
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Users user = userService.login(username, password);
		if (user != null) { // 로그인 성공
			session.setAttribute("username", username);
			result = true;
		}
		model.addAttribute("result", result);
	    model.addAttribute("user", user);
    	
    	return "/users/login";
    }
    
    
    @GetMapping("/scribbly")
    public String logout(HttpSession session) {
    	session.removeAttribute("username");
    	session.removeAttribute("password");
    	return "/main/scribbly";
    }

}
