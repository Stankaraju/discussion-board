//package com.discussionboard.controllers;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Base64;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.discussionboard.Document;
//import com.discussionboard.repositories.DocumentRepository;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping(path="api/topic")
//public class DocumentController {
//
//	
//	@Autowired
//	private DocumentRepository documentRepository;
//
//
//	@PostMapping(value="/saveDocument")
//    public @ResponseBody Document saveDocument(@RequestBody Document document) throws UnsupportedEncodingException{
//		
//		//String asB64 = Base64.getEncoder().encodeToString(document.getBytes("utf-8"));
//		
//		StringBuilder stringBuilder = new StringBuilder();
//		 
//		for (int i = 0; i < 10; ++i) {
//			stringBuilder.append(UUID.randomUUID().toString());
//		}
//
//		byte[] value = stringBuilder.toString().getBytes("utf-8");
//
//		// Encode
//		String mimeEncodedString = Base64.getMimeEncoder().encodeToString(value);
//		System.out.println(mimeEncodedString);
//		System.out.println(document.getData());
//	documentRepository.save(document);
//	
//	return document;
//	
//    }
//    @GetMapping(value="/getDocument/{id}")
//    public @ResponseBody Optional<Document> getDocument(@PathVariable long id){
//	
//	return documentRepository.findById(id);
//	
//    }
//	
//}