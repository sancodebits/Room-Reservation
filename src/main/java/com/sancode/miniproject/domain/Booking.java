

package com.sancode.miniproject.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Booking")
public class Booking {

	@Id
	@Column(name="booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="booking_date")
	private String date;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="duration_range")
	private String durationRange;
	
	@Column(name="status")
	private String status;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="deposit")
	private float deposit;
	
	@Column(name="attendees")
	private int attendees;
	
	@Column(name="registration_date")
	private String registrationDate;
	
	//client_id, room_id, layout_id
	
	@ManyToOne
	@JoinColumn
	(name="room_id")
	@JsonIgnoreProperties("image")
	private Room room;
	
	@ManyToOne
	@JoinColumn
	(name="layout_id")
	@JsonIgnoreProperties("image")
	private Layout layout;
	
	@ManyToOne
	@JoinColumn
	(name="client_id")
	private Client client;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private Collection<BookingEquipment> requiredEquipments=new ArrayList<>();
	
	@OneToMany(mappedBy = "booking")
	private Set<BookingFoodAndDrink> foodAndDrinkUnits;


	public Booking() {
		super();
	}
	
	public Booking(String date, String duration, String durationRange, String status, String paymentMethod,
			float amount, float deposit, int attendees) {
		super();
		this.date = date;
		this.duration = duration;
		this.durationRange = durationRange;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.deposit = deposit;
		this.attendees = attendees;
	}

	public void addBookingEquipment(BookingEquipment newBookingEquipment) {
		requiredEquipments.add(newBookingEquipment);
		newBookingEquipment.setBooking(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDurationRange() {
		return durationRange;
	}

	public void setDurationRange(String durationRange) {
		this.durationRange = durationRange;
	}

	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public float getDeposit() {
		return deposit;
	}


	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}


	public int getAttendees() {
		return attendees;
	}


	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Layout getLayout() {
		return layout;
	}


	public void setLayout(Layout layout) {
		this.layout = layout;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Collection<BookingEquipment> getRequiredEquipments() {
		return requiredEquipments;
	}

	public void setRequiredEquipments(Collection<BookingEquipment> requiredEquipments) {
		this.requiredEquipments = requiredEquipments;
	}

	public Set<BookingFoodAndDrink> getFoodAndDrinkUnits() {
		return foodAndDrinkUnits;
	}


	public void setFoodAndDrinkUnits(Set<BookingFoodAndDrink> foodAndDrinkUnits) {
		this.foodAndDrinkUnits = foodAndDrinkUnits;
	}
	
	
}