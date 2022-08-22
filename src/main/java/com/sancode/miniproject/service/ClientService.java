

package com.sancode.miniproject.service;

import com.sancode.miniproject.domain.Client;

public interface ClientService {
	
	Client findById(int clientId);
	
	int addNewClient(Client toBeAdded);
}
