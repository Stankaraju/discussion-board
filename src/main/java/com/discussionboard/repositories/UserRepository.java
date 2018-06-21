package com.discussionboard.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.discussionboard.User;

public interface UserRepository extends CrudRepository <User, Long> {

	
	List<User>  findByUserName(String userName);
	List<User>  findByPassword(String password);
	List<User>  findByEmail(String email);
	
}

