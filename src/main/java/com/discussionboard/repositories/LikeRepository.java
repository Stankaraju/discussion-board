package com.discussionboard.repositories;



import org.springframework.data.repository.CrudRepository;
import com.discussionboard.Likes;


public interface LikeRepository extends CrudRepository< Likes, Long> {

	void save(long l);
	//void deleteByUserAndTopic(long id, long topicId);
     Likes findByUserIdAndTopicId(long userId, long topicId);
}
