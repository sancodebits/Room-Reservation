

package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.Room;

public interface RoomService {

	List<Room> getAllRoom();
	int addNewRoom(Room toBeAdded) ;
	Room updateRoom(Room toBeUpdated,int roomId);
	Room findById(int roomId);
	String removeRoom(int roomId);
}
