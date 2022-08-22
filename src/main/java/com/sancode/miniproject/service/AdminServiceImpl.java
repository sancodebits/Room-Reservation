

package com.sancode.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.AdminDAO;
import com.sancode.miniproject.domain.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	
	AdminDAO adminDAO;
	@Autowired
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO=adminDAO;
	}
	


	@Override
	public int addNewAdmin(Admin toBeAdded) {
		if(adminDAO.hasExist(toBeAdded.getEmailId())==false) {
			Admin toBeSaved=adminDAO.saveAdmin(toBeAdded);
			return toBeSaved.getAdminId();
		}
		else {
			throw new IllegalArgumentException("The EmailId you have entered is already in use");
		}
		
	}

	@Override
	public List<Admin> getAllAdmin() {
       
		return adminDAO.getAllAdmin();
	}


	@Override
	public Admin updateAdmin(Admin toBeUpdated,int id) {
        
		return adminDAO.updateAdmin(toBeUpdated,id);
	}

}

