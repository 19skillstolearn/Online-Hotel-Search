package com.skillstolearn.onlinehotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.skillstolearn.onlinehotel.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {	
	
	public User findByUserNameAndPassword(String userName, String password);

	
}