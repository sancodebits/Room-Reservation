

package com.sancode.miniproject.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Bookingequipment")
public class BookingEquipment {
	
	@EmbeddedId 
    BookingEquipmentKey id=new BookingEquipmentKey();
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookingId")
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    Booking booking;
 
    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipment_id")
    Equipment equipment;
 
    @Column(name="units")
    int units;

	public BookingEquipment(BookingEquipmentKey id, Booking booking, Equipment equipment, int units) {
		super();
		this.id = id;
		this.booking = booking;
		this.equipment = equipment;
		this.units = units;
	}

	public BookingEquipment() {
		super();
	}

	public BookingEquipmentKey getId() {
		return id;
	}

	public void setId(BookingEquipmentKey id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "BookingEquipment [id=" + id + ", units=" + units + "]";
	}
    
    
}
