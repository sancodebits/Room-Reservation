

package com.sancode.miniproject.service;

import java.util.List;
import java.util.Set;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.RoomDAO;
import com.sancode.miniproject.domain.Layout;
import com.sancode.miniproject.domain.Room;
@Service
public class RoomServiceImpl implements RoomService {
     
	RoomDAO roomDao;
	@Autowired
	public void setRoomDao(RoomDAO roomDao) {
		this.roomDao=roomDao;
	}
	
	@Override
	public List<Room> getAllRoom() {

		List<Room>allRoom= roomDao.getAllRoom();
		for(Room l : allRoom) {
			if(l.getImageData() == null) {
				continue;
			}
			String image=new String(Base64.decodeBase64(l.getImageData()));
			l.setImage(image);
		}	 
		return allRoom;	
		
	}

	@Override
	public int addNewRoom(Room toBeAdded)  {
		String name=toBeAdded.getName();
		long object=roomDao.findByName(name);
		if(object>0) {
			throw new IllegalArgumentException("This room name is already in use, please use a different name");
		}
		if(toBeAdded.getImage()!=null) {
		String image=toBeAdded.getImage();
		toBeAdded.setImageData(Base64.encodeBase64(image.getBytes()));
		}
		return roomDao.saveRoom(toBeAdded).getRoomId();
	}

	/*
	 * Checks if a room is available with the given Id 
	 * If room exists, then updates the room and Requests the DAO layer to save the object
	 * else throws an error
	 */
	@Override
	public Room updateRoom(Room toBeUpdated,int id) {
		
		Room room = roomDao.findById(id);
		if(room == null) {
			throw new IllegalArgumentException("there is no room with given id");
		}
		
		boolean finalStatus = toBeUpdated.getStatus() != null ? toBeUpdated.getStatus() : room.getStatus();
		
		String newName = toBeUpdated.getName() != null ? toBeUpdated.getName() : room.getName();
		int finalCapacity = toBeUpdated.getCapacity() != 0 ? toBeUpdated.getCapacity() : room.getCapacity();
		
		if( (finalCapacity <= 0) || (finalCapacity > 1000) ) {
			throw new IllegalArgumentException("Please choose the capacity in the range 0 to 1000");
		}
		
		if(room.getName().compareTo(newName) != 0) {
		
			long object=roomDao.findByName(newName);
			if(object!=0) {
				throw new IllegalArgumentException("This room name is already in use, please use a different name");
			}
		}
		
		
		String image = new String(Base64.decodeBase64((room.getImageData())));
		
        room.setImage(image);
        room.setName(newName);
        room.setStatus(finalStatus);
        room.setCapacity(finalCapacity);
        roomDao.saveRoom(room);
		
        return room;
	}

	@Override
	public Room findById(int roomId) {
		return roomDao.findById(roomId);
	}

	@Override
	public String removeRoom(int roomId) {
//		roomDao.deleteById(roomId);
		
		if(roomDao.findById(roomId) != null) {
			roomDao.deleteById(roomId);
			return "Success";
		}
		else {
			throw new IllegalArgumentException("There is no room with the given name");
		}
		
	}

}
