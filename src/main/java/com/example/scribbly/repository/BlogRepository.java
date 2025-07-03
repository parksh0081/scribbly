package com.example.scribbly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.entity.Users;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    // User 기준으로 블로그 찾기 (optional)
    Optional<Blog> findByUser(Users user);

    // UserId 기준으로 블로그 찾기 (optional)
    Optional<Blog> findByUser_UserId(String userId);
}
