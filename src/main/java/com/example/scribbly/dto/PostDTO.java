package com.example.scribbly.dto;

import java.time.LocalDate;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.entity.Posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
	private Long postId;
    private Long blogId;  // Blog PK
    private String title;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDate createdAt;
    private String isDeleted;
    
 // Entity -> DTO 변환
    public static PostDTO fromEntity(Posts post) {
        if (post == null) return null;
        return PostDTO.builder()
                .postId(post.getPostId())
                .blogId(post.getBlog().getBlogId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .isDeleted(post.getIsDeleted())
                .build();
    }

    // DTO -> Entity 변환 (Blog 객체는 외부에서 셋팅)
    public Posts toEntity(Blog blog) {
        Posts post = new Posts();
        post.setPostId(this.postId);
        post.setBlog(blog);
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setLikeCount(this.likeCount);
        post.setViewCount(this.viewCount);
        post.setCreatedAt(this.createdAt);
        post.setIsDeleted(this.isDeleted);
        return post;
    }
}
