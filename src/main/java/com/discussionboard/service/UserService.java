package com.discussionboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discussionboard.User;
import com.discussionboard.repositories.UserRepository;

@Service
public class UserService {

    @Autowired	
    UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public List<User> find(String userName) {
		return userRepository.findByUserName(userName);
		
	}
	public List<User> finduser(String password){
		return userRepository.findByPassword(password);
	}
	
	public Optional<User> find(Long id) {
		return userRepository.findById(id);
	}
}