package com.discussionboard;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="document")
public class Document {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String documentName;
	private String documentType;
	@Lob
	@Column (name="data")
	private byte[] data;
    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public byte[] getBytes(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
