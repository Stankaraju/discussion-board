package com.discussionboard;

import org.springframework.web.multipart.MultipartFile;

public class DiscussionDocument {
	
	
	
	private MultipartFile image;
	private long imgId;
	private String type;
	private String imgName;
	public long getImgId() {
		return imgId;
	}
	public void setImgId(long imgId) {
		this.imgId = imgId;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	

}
