

package com.sancode.miniproject.domain;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Layout {

	@Id
	@Column(name="layout_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int layoutId;
	@Column(name="name")
	private String name;
	
	@Lob
	@Column(name="image")
	@JsonIgnore
	private byte[] imageData;
	
	//many to many relation between rooms and layout
	//roomlayoutkey or roomlayout  isliye nahi bnaya kyuki room aur layout many to many relation yahi join table karke bna diya   
	@ManyToMany
	@JoinTable(
			name="Roomlayout",
			inverseJoinColumns=@JoinColumn(name="room_id"),
			joinColumns = @JoinColumn(name="layout_id")
			
			)
	@JsonIgnoreProperties({"availableLayout", "image"})
	private Set<Room> availableRoom;
	
	@Transient
	String image;
	
	public Layout(String name, byte[] imageData, Set<Room> availableRoom) {
		super();
		this.name = name;
		this.imageData = imageData;
		this.availableRoom = availableRoom;
	}
	

	public Layout(String name, String image) {
		super();
		this.name = name;
		this.image = image;
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

	public Layout(String name) {
		
		this.name = name;
	}

	public Layout(String name, Set<Room> availableRoom) {
	
		this.name = name;
		this.availableRoom = availableRoom;
	}

	public Layout() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Room> getAvailableRoom() {
		return availableRoom;
	}

	public void setAvailableRoom(Set<Room> availableRoom) {
		this.availableRoom = availableRoom;
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}


	@Override
	public String toString() {
		return "Layout [layoutId=" + layoutId + ", name=" + name + ", imageData=" + Arrays.toString(imageData)
				+ ", availableRoom=" + availableRoom + ", image=" + image + "]";
	}
	
}