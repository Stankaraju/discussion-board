//package com.discussionboard.webconfig;
//
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//import com.discussionboard.dao.FileUploadDao;
//import com.discussionboard.dao.FileUploadDaoImpl;
//
//public class ApplicationContextConfig {
//	
//	@Autowired
//	@Bean(name="fileUploadDao")
//	public FileUploadDao getUserDao(SessionFactory sessionFactory) {
//		return new FileUploadDaoImpl(sessionFactory);
//	}
//	
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver getCommonsMultipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(20971520);   // 20MB
//	    multipartResolver.setMaxInMemorySize(1048576);  // 1MB
//	    return multipartResolver;
//	}
//	
//	@Bean(name = "dataSource")
//	public BasicDataSource getDataSource() {
//	    BasicDataSource dataSource = new BasicDataSource();
//	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	    dataSource.setUrl("jdbc:mysql://localhost:3306/discussion");
//	    dataSource.setUsername("steja");
//	    dataSource.setPassword("asdfghjkl");
//	 
//	    return dataSource;
//	}
//	
//	
//}
