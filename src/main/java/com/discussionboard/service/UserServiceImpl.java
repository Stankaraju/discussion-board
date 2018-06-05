package com.discussionboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.discussionboard.User;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user = userService.find(username);
		return   (UserDetails) user;
	}

}



























//package com.discussionboard.service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.discussionboard.User;
//import com.discussionboard.UserRepository;
//
//@Service("UserService")
//public class UserServiceImpl implements UserService {
//    
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Override
//	public User findByUserName(String userName) {
//		return (User) userRepository.findByUserName(userName);
//		return null;
//	}
//
//	@Override
//	public void saveUser(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
//		userRepository.save(user);
//	}
//
//	}
//
//}
