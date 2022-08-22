

package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.Room;

public interface RoomDAO {

	
	List<Room> getAllRoom();
	Room saveRoom(Room toBeAdded);
	Room updateRoom(Room toBeUpdated,int roomId);
	Room findById(int roomId);
	void deleteById(int roomId);
	long findByName(String name);
}
