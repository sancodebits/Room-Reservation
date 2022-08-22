

package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.FoodAndDrink;

public interface FoodAndDrinkService {

	List<FoodAndDrink> getAllFoodAndDrinkDetails();
    int  addNewFoodAndDrink(FoodAndDrink toBeAdded);
	FoodAndDrink updateFoodAndDrink(FoodAndDrink toBeUpdated,int id);
	FoodAndDrink findById(int foodAndDrinkId);

	void removeFoodAndDrink(int foodAndDrinkId);
}
