

package com.sancode.miniproject.dao;

import com.sancode.miniproject.domain.Client;

public interface ClientDAO {
	Client save(Client toBeSaved);
	
	Client findById(int id);
}