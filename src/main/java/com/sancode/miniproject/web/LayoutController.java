

package com.sancode.miniproject.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sancode.miniproject.domain.Layout;
import com.sancode.miniproject.service.LayoutService;

@RestController
//@RequestMapping("/api")
public class LayoutController {
	
	LayoutService layoutService;
	@Autowired
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	
	
	//Get /layout-all layout details -200+json body or bad request
	@GetMapping("/layouts")
	public List<Layout>  getAllLayout(){
		return layoutService.getAllLayoutDetails();
    }
	
	
	//DELETE /layout/{layoutId} - 200 or 400
	@DeleteMapping("/layouts/{layoutId}")
	public ResponseEntity removeLayout(@PathVariable int layoutId) {
		try {
			layoutService.removeLayout(layoutId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
			
	}
	
	//Put - /layout/{layoutId} -200 or 400
	@PutMapping("/layouts/{layoutId}")
	public ResponseEntity updateLayout(@PathVariable int layoutId,@RequestBody Layout toBeUpdated) {
//		Layout layoutObj=layoutService.findById(layoutId);
//		if (layoutObj == null) {
//			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
//		}
		try {
			Layout modifiedLayout=layoutService.updateLayout(toBeUpdated, layoutId);
			return new ResponseEntity<>(modifiedLayout,HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	 
	// post /rooms -201 or bad request
	@PostMapping("/layouts")
	public ResponseEntity saveNewLayout(@RequestBody Layout toBeAdded) {
		try {
			int layoutId=layoutService.addNewLayout(toBeAdded);
			System.out.println("no error, returned id: " + layoutId);
			HttpHeaders header=new HttpHeaders();
			header.setLocation(URI.create("/layout/"+layoutId));
			return new ResponseEntity(toBeAdded,header,HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			System.out.println("error occured");
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	//Get /layout-all layout details -200+json body or bad request
	@GetMapping("/user/layouts")
	public List<Layout>  getAllLayoutForUser(){
		return layoutService.getAllLayoutDetails();
    }
}
