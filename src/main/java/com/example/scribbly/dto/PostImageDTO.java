package com.example.scribbly.dto;

import java.time.LocalDate;

import com.example.scribbly.entity.PostImage;
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
public class PostImageDTO {
	private Long imageId;
    private Long postId;  // Post PK
    private String imageUrl;
    private Integer sortOrder;
    private LocalDate createdAt;
    
 	// Entity -> DTO 변환
    public static PostImageDTO fromEntity(PostImage postImage) {
        if (postImage == null) return null;
        return PostImageDTO.builder()
                .imageId(postImage.getImageId())
                .postId(postImage.getPost().getPostId())
                .imageUrl(postImage.getImageUrl())
                .sortOrder(postImage.getSortOrder())
                .createdAt(postImage.getCreatedAt())
                .build();
    }

    // DTO -> Entity 변환 (Post 객체는 외부에서 셋팅)
    public PostImage toEntity(Posts post) {
        PostImage postImage = new PostImage();
        postImage.setImageId(this.imageId);
        postImage.setPost(post);
        postImage.setImageUrl(this.imageUrl);
        postImage.setSortOrder(this.sortOrder);
        postImage.setCreatedAt(this.createdAt);
        return postImage;
    }
}
