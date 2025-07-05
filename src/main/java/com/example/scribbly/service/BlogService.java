package com.example.scribbly.service;


import org.springframework.stereotype.Service;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
	private final BlogRepository blogRepository;
	
    public Blog getBlogOrThrow(String username) {
        return blogRepository.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("해당 유저의 블로그가 존재하지 않습니다."));
    }
}
