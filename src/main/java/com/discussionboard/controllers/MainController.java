package com.discussionboard.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.discussionboard.Reply;
import com.discussionboard.Topic;
import com.discussionboard.User;
import com.discussionboard.repositories.ReplyRepository;
import com.discussionboard.repositories.TopicRepository;
import com.discussionboard.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/topic")
public class MainController {
			@Autowired
			private TopicRepository topicRepository;
			@Autowired
			private ReplyRepository replyRepository;
			@Autowired
			private UserRepository userRepository;
			
			/**             ******** TOPIC SERVICES *********           */
			
			// adding a topic
			@PostMapping(path="/add")
			public @ResponseBody Topic addNewTopic (@RequestBody Topic t) {
				topicRepository.save(t);
				return topicRepository.save(t);
			}
			
			// update a topic
			@PutMapping(path="/update")
			public @ResponseBody Topic updateTopic(@RequestBody Topic t) {
				Optional<Topic> topic = topicRepository.findById(t.getId());
				if(topic.isPresent()) {
					topic.get().settName(t.gettName());
					topic.get().setTdescription(t.getTdescription());
				}			
				return topicRepository.save(topic.get());
			}
			
			// search a topic by topic name
			@GetMapping(path="/search/tname/{tName}")
			public @ResponseBody List<Topic> getTopicByName(@PathVariable(value="tName") String tName) {
				return topicRepository.findAllBytName(tName);
				
			}
			
			// delete a topic by id
			@PostMapping (path="/delete/{id}")
			public @ResponseBody String deleteTopic(@PathVariable(value="id") long id) {
				topicRepository.deleteById(id);
				return "saved";
			}
			
			// Find all Topics
			@GetMapping(path = "/all")
			public @ResponseBody Iterable<Topic> getAllTopics(){
				return topicRepository.findAll();
			}
			
			@GetMapping(path = "/noOfTopics")
			public @ResponseBody long noOfTopics(){
				long r = topicRepository.count();
			
				return r;
			}

			// Find a topic by id
			@GetMapping(path = "/byId/{id}")
			public @ResponseBody Optional<Topic> getTopicById(@PathVariable long id){
				return topicRepository.findById(id);
			}
			
      // ADD TOPICS BY USER ID....
			@PostMapping (path="{userId}/add/topic")
			public @ResponseBody  User addNewTopicByUserId(@PathVariable(value="userId") long userId, @RequestBody Topic t){
			Optional<User> user = userRepository.findById(userId);
			User u = null;
		    if(user.isPresent()) {
		    	u = user.get();
		    	t.setUser(u);
		    	u.getTopics().add(t);
		    	userRepository.save(u); 
		    } 
				return u;
		    }

			
			/**             ******** REPLY SERVICES *********           */
			
			
			// Getting a reply by id
			@GetMapping(path="/getReplyById/{id}")
			public @ResponseBody Optional<Reply> getReplyById(@PathVariable(value="id") long id){
				return replyRepository.findById(id);
			}
			
			// Adding a comment 
			@PostMapping(path="/addReply")
			public @ResponseBody Reply addReply(@RequestBody Reply r) {
				replyRepository.save(r);
				return replyRepository.save(r);
			}
			
			// get all replies
			@GetMapping(path="/allReplies")
			public @ResponseBody Iterable<Reply> getallreplies(){
				return replyRepository.findAll();
			}
			
			//delete a reply
			@PostMapping(path="/deleteReply/{id}")
			public @ResponseBody long deleteReply(@PathVariable(value="id") long id) {
				replyRepository.deleteById(id);
				return id;
			}
					
			 //Adding a comment/Reply
			
			@PostMapping (path="{topicId}/add/reply")
			public @ResponseBody  Reply addNewReply(@PathVariable(value="topicId") long topicId, @RequestBody Reply r){
			Optional<Topic> topic = topicRepository.findById(topicId);
			Topic t = null;
		    if(topic.isPresent()) {
		    	t = topic.get();
		    	r.setTopic(t);
		    	t.getReplies().add(r);
		    	topicRepository.save(t); 
		    } 
				return r;
		    }
				
			// get Replies by topicId
			@PostMapping (path= "{topicId}/getReplies")
			public @ResponseBody List<Reply> getRepliesByTopic(@PathVariable(value="topicId") long topicId) {
				Optional<Topic> t = topicRepository.findById(topicId);
				List<Reply> replies = null;
				if (t.isPresent()) {
					replies = t.get().getReplies();
				}
				return replies;
			}	
			
			
			@PutMapping(path="/updateReply")
			public @ResponseBody Reply updateReply(@RequestBody Reply r) {
				Optional<Reply> reply = replyRepository.findById(r.getId());
				if(reply.isPresent()) {
					
					reply.get().setReplyText(r.getReplyText());
					reply.get().setCommentedBy(r.getCommentedBy());
					
				}

				
				return replyRepository.save(reply.get());
			}
			
			
			@PostMapping (path= "{userId}/getTopics")
			public @ResponseBody List<Topic> getAllTopicsByUser(@PathVariable(value="userId") long userId) {
				Optional<User> u = userRepository.findById(userId);
				List<Topic> topics = null;
				if (u.isPresent()) {
					topics = u.get().getTopics();
				}
				return topics;
			}	
			
//			@PostMapping (path= "{userId}/getTopics/{topicId}")
//			public @ResponseBody List<Topic> getTopicByUserId(@PathVariable(value="userId") long userId, @PathVariable(value="topicId") long topicId) {
//				Optional<User> u = userRepository.findById(userId);
//	
//				List<Topic> topics = null;
//				Optional<Topic> t = null;
//				
//				if (u.isPresent()) {
//					topics = u.get().getTopics();
//					
//				}
//				
//				return topics;
//			}	
//		
			/**             ******** USER SERVICES *********           */
			
			//adding a user
			@PostMapping (path="/addNewUser")
			public @ResponseBody String addNewUser(@RequestBody User u) {
				userRepository.save(u);
				return "saved";
			}
			
			// update a user
			@PutMapping(path="/updateUser")
			public @ResponseBody User updateUser(@RequestBody User u) {
				userRepository.save(u);
				return userRepository.save(u);
			}
			
			// search a topic by topic name
			@GetMapping(path="/search/userName/{userName}")
			public @ResponseBody List<User> getByUserName(@PathVariable(value="userName") String userName) {
			return userRepository.findByUserName(userName);
							
			}
}