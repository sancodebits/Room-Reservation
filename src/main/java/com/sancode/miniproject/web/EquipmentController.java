

package com.sancode.miniproject.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sancode.miniproject.domain.Equipment;
import com.sancode.miniproject.service.EquipmentService;

@RestController
public class EquipmentController {


	EquipmentService service;

	@Autowired
	public void setService(EquipmentService service) {
		this.service = service;
	}


	@PostMapping("/equipments")
	public ResponseEntity<Equipment> addNewEquipment(@RequestBody Equipment toBeAdded){
		try {
			int id = service.addNewEquipment(toBeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/equipments/"+id));
			return new ResponseEntity<Equipment>(toBeAdded,headers,HttpStatus.CREATED);
		} catch(IllegalArgumentException e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/equipments")
	public List<Equipment> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/user/equipments")
	public List<Equipment> findAllForUser(){
		return service.findAll();
	}

	@GetMapping("/equipments/{id}")
	public ResponseEntity<Equipment> getEquipmentById(@PathVariable("id") int id){
		
		try {
			Equipment e = service.findById(id);
			return new ResponseEntity<Equipment>(e, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@PutMapping("equipments/{id}")
	public ResponseEntity<Equipment> putEquipmentById(@RequestBody Equipment toBeUpdated, @PathVariable("id") int id){
		
		try {
			Equipment e = service.updateById(toBeUpdated, id);
			return new ResponseEntity<Equipment>(e, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping("equipments/{id}")
	public ResponseEntity deleteEquipmentById(@PathVariable("id") int id){
		
		try {
			service.removeEquipment(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
