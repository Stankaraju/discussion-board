package com.discussionboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.discussionboard.Document;
import com.discussionboard.Topic;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
	public List<Document> findByTopicId(Long topicId);

}
