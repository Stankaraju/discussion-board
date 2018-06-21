package com.discussionboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.discussionboard.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long> {
  
	List<Reply> findByCommentedBy (String commentedBy);
	
	
}
