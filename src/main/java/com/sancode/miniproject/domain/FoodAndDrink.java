

package com.sancode.miniproject.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name="Foodanddrink")
public class FoodAndDrink {
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	
	@Column(name="name")
	private String name;
	

	@Column(name="cost")
	private float cost;
	
	@OneToMany(mappedBy = "foodAndDrink")
	private Set<BookingFoodAndDrink> foodAndDrinkUnits;

	public FoodAndDrink(String name, float cost, Set<BookingFoodAndDrink> foodAndDrinkUnits) {
		super();
		this.name = name;
		this.cost = cost;
		this.foodAndDrinkUnits = foodAndDrinkUnits;
	}

	public FoodAndDrink() {
		super();
	}

	public Set<BookingFoodAndDrink> getFoodAndDrinkUnits() {
		return foodAndDrinkUnits;
	}

	public void setFoodAndDrinkUnits(Set<BookingFoodAndDrink> foodAndDrinkUnits) {
		this.foodAndDrinkUnits = foodAndDrinkUnits;
	}
	

	
	public FoodAndDrink( String name, boolean multiUnitAvailability, float cost) {
	
		this.name = name;
		this.cost = cost;
	}

	public int getId() {
		return foodId;
	}

	public void setId(int id) {
		this.foodId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
		
}
