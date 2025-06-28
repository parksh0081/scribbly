package com.example.scribbly.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
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
        } else {
        	// 프로필 이미지가 비어있을 경우
        	userDTO.setProfile_image("/users/defalutProfile.jpg");
        }
        
        //create_at
    	userDTO.setCreated_at(new Date());
    	
    	//회원가입
        userService.register(userDTO);
        
        return "/users/signupForm";  
    }
    
    // 로그인 폼 (Spring Security가 로그인 처리 담당)
	@GetMapping("/scribbly/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return "/users/loginForm";
    }

}
