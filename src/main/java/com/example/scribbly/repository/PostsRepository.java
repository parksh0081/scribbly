package com.example.scribbly.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.scribbly.entity.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
	// 특정 블로그 글 리스트 조회 (optional)
    List<Posts> findByBlog_BlogId(Long blogId);
    // 리스트 페이징
    Page<Posts> findByUser_UserId(String userId, Pageable pageable);
    //
    @Modifying
    @Query("UPDATE Posts p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
    void increaseViewCount(@Param("postId") Long postId);
    // 특정 블로그 & 삭제 안된 글만 조회
    List<Posts> findByBlog_BlogIdAndIsDeleted(Long blogId, String isDeleted);
}
