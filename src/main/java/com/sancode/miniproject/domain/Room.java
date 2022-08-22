

package com.sancode.miniproject.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Room {

	
	@Id
	@Column(name="room_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roomId;
	@Column(name="name")
	private String name;
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price_per_day")
	private double pricePerDay;
	@Column(name="price_per_hour")
	private double pricePerHour;
	@Column(name="price_per_half_day")
	private double pricePerHalfDay;
	@Column(name="status")
	private Boolean status;
	
	@JsonIgnore
	@Column(name="image")
	private byte[] imageData;
	
	@Transient
	String image;
	
	@ManyToMany
	@JoinTable(
			name="Roomlayout",
			joinColumns=@JoinColumn(name="room_id"),
			inverseJoinColumns = @JoinColumn(name="layout_id")
			)
	@JsonIgnoreProperties({"availableRoom", "image"})
	private Set<Layout> availableLayout;
	
	public Room() {
		super();
	}

	public Room(String name, int capacity, String description, double pricePerDay, double pricePerHour,
			double pricePerHalfDay, Boolean status, String image) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.description = description;
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.pricePerHalfDay = pricePerHalfDay;
		this.status = status;
		this.image = image;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPricePerHalfDay() {
		return pricePerHalfDay;
	}

	public void setPricePerHalfDay(double pricePerHalfDay) {
		this.pricePerHalfDay = pricePerHalfDay;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Layout> getAvailableLayout() {
		return availableLayout;
	}

	public void setAvailableLayout(Set<Layout> availableLayout) {
		this.availableLayout = availableLayout;
	}

}