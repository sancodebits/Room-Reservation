

package com.sancode.miniproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="Admin")
public class Admin {
	
	@Id
	@Column(name="admin_id")
	//@ColumnDefault("0")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(name="email_id")
	private String emailId;
	@Column(name="password")
	private String password;
	@Column(name="name")
	private String name;
	@Column(name="phone")
	private String phone;
	@Column(name="status")
	private String status;

	public Admin(String emailId, String password, String name, String phone, String status) {
		this.emailId = emailId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.status = status;
	}
	
	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



}