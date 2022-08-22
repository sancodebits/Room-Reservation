

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
import org.springframework.web.bind.annotation.RestController;

import com.sancode.miniproject.domain.Booking;
import com.sancode.miniproject.domain.FoodAndDrink;
import com.sancode.miniproject.service.BookingService;

@RestController
public class BookingController {
	
	
	BookingService service;
	@Autowired
	public void setService(BookingService service) {
		this.service = service;
	}

	//Get /Bookings-all Booking details -200+json body or bad request
	@GetMapping("/bookings")
	public List<Booking>  getAllBookings(){
		return service.getAllBookings();
    }
	
	
	//DELETE /bookings/{BookingId} - 200 or 400
	@DeleteMapping("/bookings/{BookingId}")
	public ResponseEntity removeBooking(@PathVariable int BookingId) {
//		Booking BookingObj=service.findBookingById(BookingId);
//		System.out.println(BookingObj);
//		if(BookingObj!=null) {
		try {
			service.removeBooking(BookingId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
			
	}
	
	//Put - /Bookings/{BookingId} -200 or bad request
	@PutMapping("/bookings/{id}")
	public ResponseEntity updateBooking(@PathVariable int id,@RequestBody Booking toBeUpdated) {
//		Booking booking = service.findBookingById(id);
//		if(booking == null) {
//			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
//		}
		try {
			Booking modifiedBooking=service.updateBooking(toBeUpdated, id);
			return new ResponseEntity<>(service.findBookingById(id),HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	// post /Bookings -201 or bad request
	@PostMapping("/bookings")
	public ResponseEntity saveNewBooking(@RequestBody Booking toBeAdded) {
		try {
			Booking obj=service.addNewBooking(toBeAdded);
			int id=obj.getId();
			HttpHeaders header=new HttpHeaders();
			header.setLocation(URI.create("/bookings/"+id));
			return new ResponseEntity<Booking>(obj,header,HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	 
	//Get - /bookings/{bookingId} -200 or 404
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable int id) {
		
		try {
			Booking booking=service.findBookingById(id);
			return new ResponseEntity<Booking> (booking, HttpStatus.OK);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity (e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	// post /Bookings -201 or bad request
	@PostMapping("/user/bookings")
	public ResponseEntity saveNewBookingFromUser(@RequestBody Booking toBeAdded) {
		try {
			Booking obj=service.addNewBooking(toBeAdded);
			int id=obj.getId();
			HttpHeaders header=new HttpHeaders();
			header.setLocation(URI.create("/bookings/"+id));
			return new ResponseEntity<Booking>(obj,header,HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/user/bookings")
	public List<Booking>  getAllBookingsForUser(){
		return service.getAllBookings();
    }
	
	@GetMapping("/user/bookings/{roomId}")
	public List<Booking>  getAllBookingsByRoomIdForUser(@PathVariable int roomId){
		return service.getAllBookingsByRoomId(roomId);
    }

}

