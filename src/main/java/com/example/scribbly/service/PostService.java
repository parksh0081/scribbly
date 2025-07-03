package com.example.scribbly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.scribbly.dto.PostDTO;
import com.example.scribbly.entity.Blog;
import com.example.scribbly.entity.PostImage;
import com.example.scribbly.entity.Posts;
import com.example.scribbly.repository.BlogRepository;
import com.example.scribbly.repository.PostImageRepository;
import com.example.scribbly.repository.PostsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostsRepository postRepository;
    private final BlogRepository blogRepository;
    private final PostImageRepository postImageRepository;

    @Transactional
    public Posts createPost(PostDTO postDTO, List<String> imageUrls, String userId) {
        Blog blog = blogRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("블로그 없음"));

        Posts post = postDTO.toEntity(blog);
        Posts savedPost = postRepository.save(post);

        // 이미지 저장
        int sortOrder = 1;
        for (String url : imageUrls) {
            PostImage image = PostImage.builder()
                    .post(savedPost)
                    .imageUrl(url)
                    .sortOrder(sortOrder++)
                    .build();
            postImageRepository.save(image);
        }

        return savedPost;
    }

    public List<Posts> getAllPostsByUser(String userId) {
        return postRepository.findByBlog_BlogIdAndIsDeleted(
                blogRepository.findByUser_UserId(userId)
                        .orElseThrow(() -> new RuntimeException("블로그 없음"))
                        .getBlogId(), 
                        "N"
        );
    }
    
    public Page<Posts> getPagedPostsByUser(String userId, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending());
        return postRepository.findByUser_UserId(userId, pageable);
    }
    
    public Optional<Posts> getPostById(Long postId) {
    	return postRepository.findById(postId);
    }
    
    @Transactional
    public void increaseViewCount(Long postId) {
        Posts post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        postRepository.increaseViewCount(postId);
    }

    @Transactional
    public void deletePost(Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("포스트 없음"));
        post.setIsDeleted("Y");
    }
}
