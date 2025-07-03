package com.example.scribbly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scribbly.entity.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    // 특정 게시글 이미지 리스트 조회 (optional)
    List<PostImage> findByPost_PostIdOrderBySortOrder(Long postId);
}
