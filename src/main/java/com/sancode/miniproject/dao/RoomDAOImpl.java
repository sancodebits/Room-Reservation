

package com.sancode.miniproject.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sancode.miniproject.domain.Layout;
import com.sancode.miniproject.domain.Room;
@Repository
@Transactional
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	EntityManager em;
	
	@Override
	public List<Room> getAllRoom() {
        Query q=em.createQuery("select r from Room as r");
        return q.getResultList();
	}

	@Override
	public Room saveRoom(Room toBeAdded) {
        em.persist(toBeAdded);
        return toBeAdded;
	}

	@Override
	public Room updateRoom(Room toBeUpdated,int roomId) {
        String newName=toBeUpdated.getName();
        String newDesc=toBeUpdated.getDescription();
        Boolean newStatus=toBeUpdated.getStatus();
        int newCapacity=toBeUpdated.getCapacity();
        double newPricePerDay=toBeUpdated.getPricePerDay();
        double newPricePerHour=toBeUpdated.getPricePerHour();
        double newPricePerHalfDay=toBeUpdated.getPricePerHalfDay();
        Set<Layout> newAvailableLayout = toBeUpdated.getAvailableLayout();
//        Query q=em.createQuery("update Room r set name=:newName ,description=:newDesc ,"
//        		                                + " status=:newStatus,"
//        		                                + "capacity=:newCapacity,pricePerDay=:newPricePerDay,"
//        		                                + "pricePerHour=:newPricePerHour,pricePerHalfDay=:newPricePerHalfDay"
//        		                                + " where r.roomId=:roomId ");
//        q.setParameter("newName", newName);
//        q.setParameter("newDesc", newDesc);
//        q.setParameter("newStatus", newStatus);
//        q.setParameter("newCapacity", newCapacity);
//        q.setParameter("newPricePerDay", newPricePerDay);
//        q.setParameter("newPricePerHalfDay", newPricePerHalfDay);
//        q.setParameter("newPricePerHour", newPricePerHour);
//        q.setParameter("roomId", roomId);
//        q.executeUpdate();
        Room updated = findById(roomId);
        updated.setAvailableLayout(newAvailableLayout);
        updated.setName(newName);
        updated.setDescription(newDesc);
        updated.setStatus(newStatus);
        updated.setCapacity(newCapacity);
        updated.setPricePerDay(newPricePerDay);
        updated.setPricePerHalfDay(newPricePerHalfDay);
        updated.setPricePerHour(newPricePerHour);
        saveRoom(updated);
		return updated;
	}

	@Override
	public Room findById(int roomId) {

		return em.find(Room.class,roomId);
	}

	@Override
	public void deleteById(int roomId) {
       Query q=em.createQuery("delete from Room where roomId=:roomId");
       q.setParameter("roomId", roomId);
       q.executeUpdate();
	}

	@Override
	public long findByName(String name) {
        Query q=em.createQuery("select count(*) from Room as r where name=:name");
        q.setParameter("name", name);
		return (long) q.getSingleResult();
	}

}
