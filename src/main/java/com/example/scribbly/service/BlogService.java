package com.example.scribbly.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
	private final BlogRepository blogRepository;
	
	public Optional<Blog> findByUserId(String userId) {
        return blogRepository.findByUser_UserId(userId);
    }

    public Blog getBlogOrThrow(String userId) {
        return blogRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저의 블로그가 존재하지 않습니다."));
    }
}
