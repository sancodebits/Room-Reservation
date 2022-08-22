

package com.sancode.miniproject.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookingFoodAndDrinkKey implements Serializable {
	/**
	 * 
	 */

	@Column(name = "booking_id")
    int bookingId;
 
    @Column(name = "food_id")
    int foodId;

	public BookingFoodAndDrinkKey(int bookingId, int foodId) {
		super();
		this.bookingId = bookingId;
		this.foodId = foodId;
	}

	public BookingFoodAndDrinkKey() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(bookingId, foodId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingFoodAndDrinkKey other = (BookingFoodAndDrinkKey) obj;
		return bookingId == other.bookingId && foodId == other.foodId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
    
    
    
}