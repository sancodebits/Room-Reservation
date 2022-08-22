

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

import com.sancode.miniproject.domain.Room;
import com.sancode.miniproject.service.RoomService;

@RestController
public class RoomController {
	
	RoomService roomService;
	@Autowired
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	//Get /rooms-all room details -200+json body or bad request
	@GetMapping("/rooms")
	public List<Room>  getAllRooms(){
		return roomService.getAllRoom();
     }
	
	
	//DELETE /rooms/{roomId} - 200 or 400
	@DeleteMapping("/rooms/{roomId}")
	public ResponseEntity removeRoom(@PathVariable int roomId) {
		try {
			roomService.removeRoom(roomId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	//Put - /rooms/{roomId} -200 or 204
	@PutMapping("/rooms/{roomId}")
	public ResponseEntity updateRoom(@PathVariable int roomId,@RequestBody Room toBeUpdated) {
		try {
			Room modifiedRoom=roomService.updateRoom(toBeUpdated, roomId);
			return new ResponseEntity<>(modifiedRoom,HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	// post /rooms -201 or bad request
	@PostMapping("/rooms")
	public ResponseEntity saveNewRoom(@RequestBody Room toBeAdded) {
		try {
			int id=roomService.addNewRoom(toBeAdded);
			HttpHeaders header=new HttpHeaders();
			header.setLocation(URI.create("/rooms/"+id));
			return new ResponseEntity(toBeAdded,header,HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}	
	}
	
	//Get /rooms-all room details -200+json body or bad request
	@GetMapping("/user/rooms")
	public List<Room>  getAllRoomsForUser(){
		return roomService.getAllRoom();
     }
	
}
