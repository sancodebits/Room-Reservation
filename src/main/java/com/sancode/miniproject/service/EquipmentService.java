

package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.Equipment;

public interface EquipmentService {
	Equipment findById(int equipmentId);
	
	int addNewEquipment(Equipment toBeAdded);
	
	String removeEquipment(int id);
	
	List<Equipment> findAll();
	
	Equipment updateById(Equipment toBeUpdated, int id);
}
