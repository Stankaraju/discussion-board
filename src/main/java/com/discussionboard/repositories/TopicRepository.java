package com.discussionboard.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.discussionboard.Topic;


public interface TopicRepository extends CrudRepository<Topic, Long> {
	
	List<Topic> findById(String tname);
	List<Topic> findAllBytName(String tname);
	
//	void save(Reply r);
	Topic save(Topic topic);
	
}