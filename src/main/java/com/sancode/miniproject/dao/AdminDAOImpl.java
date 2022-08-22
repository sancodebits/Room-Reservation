

package com.sancode.miniproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sancode.miniproject.domain.Admin;
@Repository
@Transactional
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	EntityManager em;

	@Override
	public Admin findByEmailId(String emailId) {
        
		Query q=em.createQuery("select a from Admin a where a.emailId=:emailId");
		q.setParameter("emailId", emailId);
		Admin expectedAdmin=(Admin) q.getSingleResult();
		return expectedAdmin;
	}

	@Override
	public Admin saveAdmin(Admin toBeAdded) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		toBeAdded.setPassword(encoder.encode(toBeAdded.getPassword()));
        em.persist(toBeAdded);
        return toBeAdded;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAllAdmin() {
		Query q=em.createQuery("select a from Admin as a");
		return q.getResultList();
	}

	@Override
	public Admin updateAdmin(Admin toBeUpdated,int adminId) {
		String newStatus=toBeUpdated.getStatus();
		Query q=em.createQuery("update Admin a set status=:newStatus  where a.adminId=:adminId ");
		
		q.setParameter("newStatus", newStatus);
		q.setParameter("adminId", adminId);
		q.executeUpdate();
		
		return em.find(Admin.class,adminId);
		
	}

	@Override
	public boolean hasExist(String emailId) {
        Query q=em.createQuery("select count(*) from Admin as a where emailId=:emailId");
        q.setParameter("emailId", emailId);
		long count=(long) q.getSingleResult();
		return (count>0) ? true : false ;
	}


	

}