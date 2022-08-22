

package com.sancode.miniproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.AdminDAO;
import com.sancode.miniproject.domain.Admin;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	AdminDAO adminDAO;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Admin admin=adminDAO.findByEmailId(username);
		 return new User(admin.getEmailId(),admin.getPassword(),new ArrayList<>());
	}

}
