package com.discussionboard.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.discussionboard.Likes;
import com.discussionboard.Topic;
import com.discussionboard.User;
import com.discussionboard.repositories.LikeRepository;
import com.discussionboard.repositories.TopicRepository;
import com.discussionboard.repositories.UserRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/topic")
public class LikeController {

	@Autowired
	private LikeRepository likeRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserRepository userRepository;
	
	
//	@PostMapping (path="/likes")
//	public @ResponseBody Long numberOfLikes(@RequestBody long  likes) {
//		likes = likeRepository.count();
//		long l = likes + 1;
//		likeRepository.save(l);
//		return l;
//	}
	
	// deleting a 
	@PostMapping (path="{topicId}/delete/like")
	public @ResponseBody  Likes deleteLike(@PathVariable(value="topicId") long topicId){
	
		
		return null;
    }
	
	// adding a new like
	@PostMapping (path="{topicId}/add/like")
	public @ResponseBody  Likes addNewLike(@PathVariable(value="topicId") long topicId){
	Optional<Topic> topic = topicRepository.findById(topicId);
	Topic t = null;
	Likes l = new Likes();
    if(topic.isPresent()) {
    	t = topic.get();
    	l.setTopic(t);
    	t.getLikes().add(l);
    	topicRepository.save(t); 
    } 
		return l ;
    }
	
	// adds like to a topic by user
	@PostMapping (path="{userId}/addLike/{topicId}")
	public @ResponseBody Likes addUserLikes(@PathVariable(value ="userId") long userId,@PathVariable(value ="topicId") long topicId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Topic> topic = topicRepository.findById(topicId);
		User u = null;
		Topic t = null;
		Likes l = new Likes();
		if(user.isPresent()) {
			u = user.get();
			if (topic.isPresent()) {
				t = topic.get();				
				l.setTopic(t);
				l.setUser(u);				
				t.setUser(u);
				u.getLikes().add(l); 
		userRepository.save(u);
	
			}		
		}		
		return l;
		
	}

  // gets the topics likes by user by taking userId and topicId
	
	@PostMapping(path="/{userId}/topicsByUserLikes")
	public @ResponseBody List<Likes> getTopcisByUserLike(@PathVariable(value="userId") long userId) {
		
		Optional<User> user = userRepository.findById(userId);
		//Optional<Topic> topic = topicRepository.findById(topicId);
		User u = null;
		List<Topic> t = null;
		List<Likes> l = null;
		if(user.isPresent()) {
			u = user.get();
			l = u.getLikes();
			
		}
			
		return l;
	}
	
	// Delete a like by querying like by userId and topicId
	@PostMapping(path="{userId}/deletelike/{topicId}")
	public @ResponseBody Likes deleteLikebyId(@PathVariable(value="userId") long userId, @PathVariable(value="topicId") long topicId) {
//		Optional <User> user = userRepository.findById(userId);
//		Optional <Topic> topic = topicRepository.findById(topicId);
//		User u = null;
//		Topic t = null;
//		if(user.isPresent()) {
//			user.get();
//			
//		}
		likeRepository.deleteByUserAndTopic(topicId, userId);
		
		
		
		return null;
	}
	
	
}
