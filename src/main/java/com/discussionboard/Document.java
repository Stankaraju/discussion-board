package com.discussionboard;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="document")
public class Document {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "type")
	private String type;
	@Lob
	@Column(name= "content")
	private byte[] content;
	
	//**** relation to topic ****//
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="topic_id")
	private Topic topic;
	
	public Document() {
		
	}
	public Document(long id, String name, String type, byte[] content) {
		this.id= id;
		this.name=name;
		this.type= type;
		this.content=content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
