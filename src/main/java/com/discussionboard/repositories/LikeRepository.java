package com.discussionboard.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.discussionboard.Likes;
import com.discussionboard.Topic;
import com.discussionboard.User;

public interface LikeRepository extends CrudRepository< Likes, Long> {

	void save(long l);
	void deleteByUserAndTopic(long userId, long topicId);
	
}
