
package com.sancode.miniproject.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//embedded and embeddable use together , used for injecting in another class.
@Embeddable
public class BookingEquipmentKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2771017855202407219L;

    int bookingId;
    int equipmentId;

	@Override
	public int hashCode() {
		return Objects.hash(bookingId, equipmentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingEquipmentKey other = (BookingEquipmentKey) obj;
		return bookingId == other.bookingId && equipmentId == other.equipmentId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public BookingEquipmentKey(int bookingId, int equipmentId) {
		super();
		this.bookingId = bookingId;
		this.equipmentId = equipmentId;
	}

	public BookingEquipmentKey() {
		super();
	}
    
}
