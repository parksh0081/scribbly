package com.example.scribbly.dto;

import com.example.scribbly.entity.Blog;
import com.example.scribbly.entity.Users;

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
public class BlogDTO {
	private Long blogId;
    private String userId;  // User PK
    private String blogTitle;
    
    // Entity -> DTO 변환
    public static BlogDTO fromEntity(Blog blog) {
        if (blog == null) return null;
        return BlogDTO.builder()
                .blogId(blog.getBlogId())
                .userId(blog.getUser().getUserId())
                .blogTitle(blog.getBlogTitle())
                .build();
    }

    // DTO -> Entity 변환 (User 객체는 외부에서 셋팅해야 함)
    public Blog toEntity(Users user) {
        Blog blog = new Blog();
        blog.setBlogId(this.blogId);
        blog.setUser(user);
        blog.setBlogTitle(this.blogTitle);
        return blog;
    }
}
