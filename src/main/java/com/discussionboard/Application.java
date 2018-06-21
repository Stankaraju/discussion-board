package com.discussionboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.discussionboard.service.StorageService;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableJpaAuditing

public class Application implements CommandLineRunner  {
	
	@Resource
	StorageService storageService;

	
	public static void main(String[]args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
