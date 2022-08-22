

package com.sancode.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.FoodAndDrinkDAO;
import com.sancode.miniproject.domain.FoodAndDrink;

@Service
public class FoodAndDrinkServiceImpl implements FoodAndDrinkService {

	FoodAndDrinkDAO dao;
	@Autowired
	public void setDao(FoodAndDrinkDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<FoodAndDrink> getAllFoodAndDrinkDetails() {

		return dao.getAllFoodAndDrinkDetails();
	}

	@Override
	public int addNewFoodAndDrink(FoodAndDrink toBeAdded) {

		return dao.saveNewFoodAndDrink(toBeAdded).getId();
	}

	@Override
	public FoodAndDrink updateFoodAndDrink(FoodAndDrink toBeUpdated,int id) {

		return dao.updateFoodAndDrink(toBeUpdated, id);
	}

	@Override
	public FoodAndDrink findById(int foodAndDrinkId) {
		return dao.findById(foodAndDrinkId);
	}

	@Override
	public void removeFoodAndDrink(int foodAndDrinkId) {

		dao.deleteById(foodAndDrinkId);
	}

}
