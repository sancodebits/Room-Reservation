
package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.Admin;

public interface AdminService {

	public int addNewAdmin(Admin toBeAdded); 
	public List<Admin> getAllAdmin();
	public Admin updateAdmin(Admin toBeUpdated,int id);
}