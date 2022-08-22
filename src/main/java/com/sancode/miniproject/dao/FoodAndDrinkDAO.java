
package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.FoodAndDrink;

public interface FoodAndDrinkDAO {

	List<FoodAndDrink> getAllFoodAndDrinkDetails();
    FoodAndDrink saveNewFoodAndDrink(FoodAndDrink toBeAdded);
	FoodAndDrink updateFoodAndDrink(FoodAndDrink toBeUpdated,int id);
	FoodAndDrink findById(int foodAndDrinkId);
	void deleteById(int foodAndDrinkId);
}
