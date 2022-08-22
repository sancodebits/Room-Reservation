package com.sancode.miniproject.web;
///*******************************************************************************
// *
// *
//
//package com.adobe.miniproject.web;
//
//import java.net.URI;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.adobe.miniproject.domain.FoodAndDrink;
//import com.adobe.miniproject.service.FoodAndDrinkService;
//
//@RestController
//public class FoodAndDrinkController {
//	
//	FoodAndDrinkService service;
//	@Autowired
//	public void setService(FoodAndDrinkService service) {
//		this.service = service;
//	}
//	
//	
//	//Get /FoodAndDrinks-all FoodAndDrink details -200+json body or bad request
//	@GetMapping("/FoodAndDrinks")
//	public List<FoodAndDrink>  getAllFoodAndDrinks(){
//		return service.getAllFoodAndDrinkDetails();
//     }
//	
//	
//	//DELETE /FoodAndDrinks/{FoodAndDrinkId} - 204 or 404
//	@DeleteMapping("/FoodAndDrinks/{FoodAndDrinkId}")
//	public ResponseEntity removeFoodAndDrink(@PathVariable int FoodAndDrinkId) {
//		FoodAndDrink FoodAndDrinkObj=service.findById(FoodAndDrinkId);
//		if(FoodAndDrinkObj!=null) {
//			try {
//				service.removeFoodAndDrink(FoodAndDrinkId);
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}catch(IllegalArgumentException e) {
//				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//			}
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//			
//	}
//	
//	//Put - /FoodAndDrinks/{FoodAndDrinkId} -200 or bad request
//	@PutMapping("/FoodAndDrinks/{id}")
//	public ResponseEntity updateFoodAndDrink(@PathVariable int id,@RequestBody FoodAndDrink toBeUpdated) {
//		try {
//			FoodAndDrink modifiedFoodAndDrink=service.updateFoodAndDrink(toBeUpdated, id);
//			return new ResponseEntity<>(modifiedFoodAndDrink,HttpStatus.OK);
//		}catch(IllegalArgumentException e) {
//			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	// post /FoodAndDrinks -200 or bad request
//	@PostMapping("/FoodAndDrinks")
//	public ResponseEntity saveNewFoodAndDrink(@RequestBody FoodAndDrink toBeAdded) {
//		try {
//			int id=service.addNewFoodAndDrink(toBeAdded);
//			HttpHeaders header=new HttpHeaders();
//			header.setLocation(URI.create("/FoodAndDrinks/"+id));
//			return new ResponseEntity(toBeAdded,header,HttpStatus.OK);
//		}
//		catch(IllegalArgumentException e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//	}
//}