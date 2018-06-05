package com.discussionboard.dao;

import java.util.List;

import com.discussionboard.Document;

public interface UploadFileDao {
	  List<Document> findAll();
	     
	    Document findById(int id);
	     
	    void save(Document document);
	     
	    List<Document> findAllByUserId(int userId);
	     
	    void deleteById(int id);
}
