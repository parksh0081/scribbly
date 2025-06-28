package com.example.scribbly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.scribbly.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String>{
	
	Optional<Users> findByUsername(String username);
}
