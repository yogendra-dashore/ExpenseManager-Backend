package com.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	List<UserEntity> findAll();
	UserEntity findByEmail(String email);
	UserEntity findByEmailAndPassword(String email, String password);
	UserEntity findByOtp(Integer otp);
	Optional<UserEntity> findBytoken(String token);
	
	
	
	
	
	
}
