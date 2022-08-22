

package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.Equipment;

public interface EquipmentDAO {
	Equipment save(Equipment tobeSaved);
	
	Equipment updateById(Equipment toBeUpdated, int id);
	
	Equipment findById(int id);
	
	void deleteById(int id);
	
	List<Equipment> findAll();
	
	long findByName(String name);
}
