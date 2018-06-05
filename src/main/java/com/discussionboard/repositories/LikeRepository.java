package com.discussionboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.discussionboard.Likes;

public interface LikeRepository extends CrudRepository< Likes, Long> {


	

	void save(long l);
	
//	@Query("select count(likes) from Likes as l where l.topic_id = :id") 
//	 public List<Likes> likesById (@Param(value = "topic_id") long topic_id);
	
 
}
