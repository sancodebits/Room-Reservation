

package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.Admin;

public interface AdminDAO {
	 Admin findByEmailId(String emailId);
	 Admin saveAdmin(Admin toBeAdded); 
	 Admin updateAdmin(Admin toBeUpdated,int adminId);
	 List<Admin> getAllAdmin();
	 boolean hasExist(String emailId);
	
    
}