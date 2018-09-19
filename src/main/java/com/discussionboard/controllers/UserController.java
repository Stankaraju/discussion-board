package com.discussionboard.controllers;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.discussionboard.User;
// import com.discussionboard.repositories.UserRepository;
import com.discussionboard.service.CustomErrorType;
import com.discussionboard.service.UserService;

@RestController
@RequestMapping(path="user")
public class UserController {
		public static final Logger logger = LoggerFactory.getLogger(UserController.class);
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private SimpleMailController simpleMailController;
//		@Autowired
//		private UserRepository userRepository;
//		
		
		// user Registration service..
		
		@CrossOrigin
		@RequestMapping(path = "/register", method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody User newUser) {
			logger.info("UserName:" + newUser.getUserName());
			logger.info("userService :" + userService.find(newUser.getUserName()) );
			List<User> users = userService.find(newUser.getUserName());

			if (users != null && !users.isEmpty() ) {
				logger.error("username Already exist " + newUser.getUserName());
				return new ResponseEntity<>(
						new CustomErrorType("user with username " + newUser.getUserName() + "already exist "),
						HttpStatus.CONFLICT);
			}
			
			newUser.setRole("USER");
			
			simpleMailController.sendMail(newUser.getEmail(),"Hello"+" "+newUser.getUserName()); // Sends an email to the newly registered user. 
			
			return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
		}
		
		// user login service..
		
		@CrossOrigin
		@PostMapping(path= "/login")
		public User loginUser(@RequestBody User user){
			
			User loggedUser = null;
			
			logger.info("UserName"+ user.getUserName());
			logger.info("userService :" + userService.find(user.getUserName()) );
			List<User> users = userService.find(user.getUserName());
			for (User u : users) {
				System.out.println("*123 --> user : " + u.toString());
			}
			if (users != null && !users.isEmpty() && users.size()==1 ) {
				if( user.getUserName().equalsIgnoreCase( users.get(0).getUserName()) && user.getPassword().equalsIgnoreCase( users.get(0).getPassword()) ) {
					loggedUser = users.get(0);
				}
					
		    }

		return loggedUser;
		}
		

		
}
