

package com.sancode.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.ClientDAO;
import com.sancode.miniproject.domain.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	ClientDAO dao;
	
	@Autowired
	public void setDao(ClientDAO dao) {
		this.dao = dao;
	}

	@Override
	public Client findById(int clientId) {
		
		return dao.findById(clientId);
	}

	@Override
	public int addNewClient(Client toBeAdded) {
		Client added = dao.save(toBeAdded);
		return added.getClientId();
	}

}
