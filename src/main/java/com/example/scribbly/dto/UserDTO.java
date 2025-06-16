package com.example.scribbly.dto;

import java.util.Date;

import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.example.scribbly.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDTO {
    
	private String username;
	private String password;
	private String email;
	
}
