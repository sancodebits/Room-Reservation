

package com.sancode.miniproject.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Equipment")
public class Equipment {
	
	@Id
	@Column(name="equipment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int equipmentId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="multi_unit_availability")
	private boolean multiUnitAvailability;
	
	@Column(name="cost")
	private String cost;
	
	@OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<BookingEquipment> equipmentUnits;

	public Equipment(String name, boolean multiUnitAvailability, String cost) {
		super();
		this.name = name;
		this.multiUnitAvailability = multiUnitAvailability;
		this.cost = cost;
	}

	public Equipment(String name, boolean multiUnitAvailability, String cost,
			Set<BookingEquipment> equipmentUnits) {
		super();
		this.name = name;
		this.multiUnitAvailability = multiUnitAvailability;
		this.cost = cost;
		this.equipmentUnits = equipmentUnits;
	}
	
	public Equipment() {
		super();
	}

	public int getId() {
		return equipmentId;
	}

	public void setId(int id) {
		this.equipmentId = id;
	}

	public Set<BookingEquipment> getEquipmentUnits() {
		return equipmentUnits;
	}

	public void setEquipmentUnits(Set<BookingEquipment> equipmentUnits) {
		this.equipmentUnits = equipmentUnits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMultiUnitAvailability() {
		return multiUnitAvailability;
	}

	public void setMultiUnitAvailability(boolean multiUnitAvailability) {
		this.multiUnitAvailability = multiUnitAvailability;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", name=" + name + ", multiUnitAvailability="
				+ multiUnitAvailability + ", cost=" + cost + "]";
	}
	
}
