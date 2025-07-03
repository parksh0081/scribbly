package com.example.scribbly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.entity.Posts;
import com.example.scribbly.entity.Users;
import com.example.scribbly.security.UsersPrincipal;
import com.example.scribbly.service.BlogService;
import com.example.scribbly.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/scribbly")
@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;
    private final PostService postService;

    @GetMapping("/{userId}")
    public String showUserBlog(@PathVariable String userId, Model model,
    						   @RequestParam(defaultValue = "0") int page,
                               @AuthenticationPrincipal UsersPrincipal principal) {
        if (principal == null) return "redirect:/scribbly/login?error=true";

        // 로그인한 유저의 userId가 아니어도 접근할 수 있게 됨
        Blog blog = blogService.getBlogOrThrow(userId);
        model.addAttribute("blogTitle", blog.getBlogTitle());
        // post목록
        List<Posts> posts = postService.getAllPostsByUser(userId);
        model.addAttribute("posts", posts);
        // 유저정보
        Users user = principal.getUsers();
        model.addAttribute("user", user);
        // 페이징 처리된 포스트 목록
        Page<Posts> postPage = postService.getPagedPostsByUser(userId, page);
        model.addAttribute("postPage", postPage);
        return "blog/blog";
    }


	// 글 목록 fragment (Ajax로 부분 로딩용)
    @GetMapping("/{userId}/postsFragment")
    public String postListFragment(@PathVariable String userId, Model model) {
        List<Posts> posts = postService.getAllPostsByUser(userId);
        model.addAttribute("posts", posts);
        return "blog/postList :: postListFragment"; // Thymeleaf fragment 명시
    }

    // 글쓰기 폼 fragment (Ajax용)
    @GetMapping("/{userId}/writeFragment")
    public String writeFormFragment(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        return "blog/postWrite :: postWriteFragment"; // fragment 지정
    }
    
    // 글 상세보기
    @GetMapping("/{userId}/postFragment/{postId}")
    public String showPostDetail(@PathVariable Long postId, Model model) {
        postService.increaseViewCount(postId);
        Posts post = postService.getPostById(postId)
                    .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        model.addAttribute("post", post);
        return "blog/postDetail :: postDetailFragment";
    }

}
