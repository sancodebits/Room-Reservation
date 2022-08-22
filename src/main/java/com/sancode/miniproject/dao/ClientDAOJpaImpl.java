

package com.sancode.miniproject.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sancode.miniproject.domain.Client;


@Repository
@Transactional
public class ClientDAOJpaImpl implements ClientDAO {

	
	@Autowired
	EntityManager em;
	
	

	@Override
	public Client save(Client toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Client findById(int id) {
		return em.find(Client.class, id);
	}
	

}
