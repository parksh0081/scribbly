package com.example.scribbly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.scribbly.dto.PostDTO;
import com.example.scribbly.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsRestController {
	private final PostService postService;

    // 글 등록
    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String content,
            @RequestBody(required = false) List<String> imageUrls // body로 이미지 리스트 받기
    ) {
        PostDTO postDTO = PostDTO.builder()
                .title(title)
                .content(content)
                .isDeleted("N")
                .build();

        postService.createPost(postDTO, imageUrls != null ? imageUrls : new ArrayList<>(), userId);
        return ResponseEntity.ok("글 등록 완료");
    }

    // 글 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable String userId) {
        List<PostDTO> posts = postService.getAllPostsByUser(userId).stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("삭제 완료");
    }
}