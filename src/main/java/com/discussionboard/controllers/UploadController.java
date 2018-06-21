package com.discussionboard.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.discussionboard.Document;
import com.discussionboard.Topic;
import com.discussionboard.repositories.DocumentRepository;
import com.discussionboard.repositories.TopicRepository;


 
@RestController
public class UploadController {
 
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	// save a document to database
	@RequestMapping(value="/documentupload",method=RequestMethod.POST, consumes= {"multipart/form-data"})
	@ResponseBody
	public List<Document> docUpload(HttpServletRequest request ,@RequestParam("files") MultipartFile[] files) throws IOException	{
	
	List<Document> documentList=new ArrayList<>();
		for(MultipartFile file : files) {
			byte[] content = file.getBytes();
			Document backImg= new Document();
			backImg.setName(file.getOriginalFilename());
			backImg.setType(file.getContentType());
			backImg.setContent(content);
			documentList.add(backImg);
		documentRepository.save(backImg);	
		}
	return documentList;
}
 
	// get a document from database by id
	@GetMapping(path="/getdocuments/{docId}")
	@ResponseBody
	public Optional<Document> getDocs(@PathVariable (value="docId") long docId){
		return documentRepository.findById(docId);
		
	}
	
	// Get all documents from db
	@GetMapping(path="/getAllDocuments")
	@RequestMapping
	public Iterable<Document> getAllDoc(){
		return documentRepository.findAll();
	}
	
     // attaches a file to the new document by taking the topicId from the link..
	@RequestMapping(value="/docuupload/{topicId}",method=RequestMethod.POST, consumes= {"multipart/form-data"})
	@ResponseBody
	public  List<Document> topicDocUpload(HttpServletRequest request ,@RequestParam("files") MultipartFile[] files, @PathVariable(value="topicId") long topicId) throws IOException	{
	Optional<Topic> topic = topicRepository.findById(topicId);
	Topic t = null;
	
	List<Document> documentList=new ArrayList<>();
	if(topic.isPresent()) {
		t = topic.get();
			
		}
      		for(MultipartFile file : files) {
			byte[] content = file.getBytes();
			Document backImg= new Document();
			backImg.setName(file.getOriginalFilename());
			backImg.setType(file.getContentType());
			backImg.setContent(content);
			backImg.setTopic(t);
			documentList.add(backImg);
		documentRepository.save(backImg);
	}
	
	return documentList;
}

	// Downloading a document 
	 @RequestMapping(value = { "/download-document/{docId}" }, method = RequestMethod.GET)
	    public String downloadDocument( @PathVariable(value="docId") long docId, HttpServletResponse response) throws IOException {
	        Optional<Document> document = documentRepository.findById(docId);
	        Document doc = null;
	        if(document.isPresent()) {
	        	doc = document.get();
	        	}
	        response.setContentType(doc.getType());
	        response.setContentLength(doc.getContent().length);
	        response.setHeader("Content-Disposition","attachment; filename=\"" + doc.getName() +"\"");
	  
	        FileCopyUtils.copy(doc.getContent(), response.getOutputStream());
	  
	        return "saved";
	    }
	 
	 // get documents by topicId
	 @GetMapping(value="/getdocbytopicId/{topicId}")
	 public List<Document> getDocByTopicId(@PathVariable(value="topicId") long topicId){
		 List<Document> document = documentRepository.findByTopicId(topicId);
		 
		 
		 return document;
	 }
	
	
}

