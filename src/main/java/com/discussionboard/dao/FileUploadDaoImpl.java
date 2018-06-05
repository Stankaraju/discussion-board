//package com.discussionboard.dao;
//import javax.transaction.Transactional;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.discussionboard.Document;
//
//@Repository
//public class FileUploadDaoImpl implements FileUploadDao {
//	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	public FileUploadDaoImpl() {
//		
//	}
//	
//	public FileUploadDaoImpl(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	@Override
//	@Transactional
//	public void save(Document document) {
//		sessionFactory.getCurrentSession().save(document);
//	}
//
//}
