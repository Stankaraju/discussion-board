package com.discussionboard;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="likes")
public class Likes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long likes;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="topic_id")
	private Topic topic;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public void setTopic(User u) {
		// TODO Auto-generated method stub
		
	}


}
