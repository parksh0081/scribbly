package com.example.scribbly.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.scribbly.entity.Users;
import com.example.scribbly.repository.UsersRepository;

@Repository
public class UserDAO {
	@Autowired
	UsersRepository usersRepository;
	
	// 아이디중복검사
	public boolean isExistId(String username) {
		return usersRepository.findByUsername(username).isPresent();
	}
	
	
}
