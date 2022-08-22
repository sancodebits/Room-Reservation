

package com.sancode.miniproject.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name="Bookingfoodanddrink")
public class BookingFoodAndDrink {
	@EmbeddedId
	BookingFoodAndDrinkKey id=new BookingFoodAndDrinkKey();
	
	@ManyToOne
    @MapsId("bookingId")
    @JoinColumn(name = "booking_id")
    Booking booking;
 
    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    FoodAndDrink foodAndDrink;
 
    @Column(name="units")
    int units;
    
    

	public BookingFoodAndDrink() {
		super();
	}

	public BookingFoodAndDrink(Booking booking, FoodAndDrink foodAndDrink, int units) {
		super();
		this.booking = booking;
		this.foodAndDrink = foodAndDrink;
		this.units = units;
	}


	public BookingFoodAndDrinkKey getId() {
		return id;
	}

	public void setId(BookingFoodAndDrinkKey id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public FoodAndDrink getFoodAndDrink() {
		return foodAndDrink;
	}

	public void setFoodAndDrink(FoodAndDrink foodAndDrink) {
		this.foodAndDrink = foodAndDrink;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
    
    
}
