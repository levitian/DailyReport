package com.mottledog.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: User 
 * @Description: user entity
 * @author tianli 
 * @date 2015-1-21 14:18:22 
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -854530626840992392L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer userId;

	@Column(name = "NAME", unique=true)
	private String username;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PASSWD", nullable = false)
	private String password;
	
	@Column(name = "PHONE")
	private String phone;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
