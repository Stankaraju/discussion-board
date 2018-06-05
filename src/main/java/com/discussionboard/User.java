package com.discussionboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.persistence.*;

import javax.transaction.Transactional;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Transactional
@Table(name="User")
public  class User implements UserDetails {

	public static enum Role { USER }

//	private static final String Username = null;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique = true)
	private String userName;
	
	@Column (unique = true)
	private String email;
	
	@JsonProperty (access = Access.WRITE_ONLY)
	private String password; 
	
	private String role;

	@OneToMany (cascade = CascadeType.ALL,
		    fetch = FetchType.LAZY,
		    mappedBy="user")

private List <Likes> likes;


	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public User() {
		
	}
	 public User(User users) {
	    
	        this.email = users.getEmail();
	
	        this.userName = users.getUserName();
	     
	        this.id = users.getId();
	        this.password = users.getPassword();
	    }

	 
	 @JsonIgnore
		public boolean isEnabled() {
			return true;
		}

		@JsonIgnore
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@JsonIgnore
		public boolean isAccountNonLocked() {
			return true;
		}

		@JsonIgnore
		public boolean isAccountNonExpired() {
			return true;
		}

		@JsonIgnore
		
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));
			return authorities;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role +
					 ",]";
		}
	

	@OneToMany (cascade = CascadeType.ALL,
		        fetch = FetchType.LAZY,
		        mappedBy="user")
    private List <Topic> topics ;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
