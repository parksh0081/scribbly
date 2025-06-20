package com.example.scribbly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.scribbly.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String>{
	@Query(value="select * from users where username = :username", nativeQuery = true)
	Optional<Users> findByUsername(@Param("username") String username);
	
	@Query(value="select * from users where username = :username and password=:password", nativeQuery = true)
	Users findByIDAndPW(@Param("username") String username, @Param("password") String password);
	
	
}
