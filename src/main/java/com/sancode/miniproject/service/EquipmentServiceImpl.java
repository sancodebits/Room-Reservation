

package com.sancode.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.EquipmentDAO;
import com.sancode.miniproject.domain.Equipment;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	EquipmentDAO dao;

	@Autowired
	public void setDao(EquipmentDAO dao) {
		this.dao = dao;
	}


	@Override
	public Equipment findById(int equipmentId) {
		Equipment existing = dao.findById(equipmentId);
//		System.out.println("");
		if(existing != null) {
			return existing;
		}
		else {
			throw new IllegalArgumentException("There is no equipment with given id");
		}
	}

	@Override
	public int addNewEquipment(Equipment toBeAdded) {
		long count=dao.findByName(toBeAdded.getName());
		if(count!=0) {
			throw new IllegalArgumentException("Equipment name is already in use, please use a different name");
		}
		Equipment added = dao.save(toBeAdded);
		return added.getId();
	}

	@Override
	public String removeEquipment(int id) {
		Equipment existing = dao.findById(id);
		if(existing != null) {
			dao.deleteById(id);
			return "success";
		}
		else {
			throw new IllegalArgumentException("there is no equipment with given id");
		}
//		try {
//			Equipment existing = findById(id);
//			dao.deleteById(1);
//		}catch(IllegalArgumentException e) {
//			throw e;
//		}

	}

	@Override
	public List<Equipment> findAll() {
		return dao.findAll();
	}

	@Override
	public Equipment updateById(Equipment toBeUpdated, int id) {
		Equipment existing = dao.findById(id);
		if(existing != null) {
			String newName = toBeUpdated.getName() != null ? toBeUpdated.getName() : existing.getName();
			String newCost = toBeUpdated.getCost() != null ? toBeUpdated.getCost() : existing.getCost();
			boolean newMultiUnitAvailability = toBeUpdated.isMultiUnitAvailability();
			
			if(isCostValid(newCost).compareTo("Success") != 0) {
				throw new IllegalArgumentException(isCostValid(newCost));
			}
			
			if(existing.getName().compareTo(newName) != 0) {
				long count=dao.findByName(toBeUpdated.getName());
				if(count!=0) {
					throw new IllegalArgumentException("Equipment name is already in use, please use a different name");
				}
			}
			
			existing.setCost(newCost);
			existing.setName(newName);
			existing.setMultiUnitAvailability(newMultiUnitAvailability);
			
			return dao.save(existing);
			
			
		}
		else {
			throw new IllegalArgumentException("there is no equipment with that id");
		}
	}


	private String isCostValid(String newCost) {
		String[] costArr = newCost.split(" ",5);
		if(costArr.length != 3) {
			return "Please enter the cost in correct format (cost per booking or hour)";
		}
		try {
			int cost = Integer.parseInt(costArr[0]);
			if( (cost <= 0) || (cost > 1000) ) {
				return "Please choose the cost in the range 0 to 1000";
			}
			
		}catch(NumberFormatException e) {
			return "Cost has to be a positive integer in the range 0 to 1000";
		}
		
		if( ("per".compareTo(costArr[1]) == 0) && ( ("hour".compareTo(costArr[2]) == 0)  || ("booking".compareTo(costArr[2]) == 0) ) ) {
			return "Success";
		}
		
		return "Please enter the cost in correct format (cost per booking or hour)";
	}

}
