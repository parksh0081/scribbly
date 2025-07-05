package com.example.scribbly.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users {
	
    @Id
    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;  // 닉네임

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "profile_image", length = 255)
    private String profile_image;

    @Column(name = "blog_title", length = 100)
    private String blog_title;  // 블로그 타이틀

    @Column(name = "role", length = 20, nullable = false)
    private String role;  // 관리자 구분, 예: USER, ADMIN

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date last_login;
    
    @Override
    public String toString() {
        return "Users{" + "user_id=" + userId + ", username=" + username + "}";
    }
}
