

package com.sancode.miniproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sancode.miniproject.domain.Booking;
@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO {
    @Autowired
	EntityManager em;
	
    @Override
    public Booking findBookingById(int id) {
    	return em.find(Booking.class, id);
    }
	@Override
	public List<Booking> getAllBooking() {
         Query q=em.createQuery("select b from Booking as b");
		return q.getResultList(); 
	}

	@Override
	public Booking saveBooking(Booking toBeAdded) {
         em.persist(toBeAdded);
		return toBeAdded;
	}

	@Override
	public Booking updateBooking(Booking toBeUpdated, int id) {
         String newStatus=toBeUpdated.getStatus();
         float newAmount = toBeUpdated.getAmount();
         String newDate = toBeUpdated.getDate();
         synchronized (em) {
        	 
        	 Query q=em.createQuery("update Booking b set b.status=:newStatus"
              		+ ", b.amount=:newAmount, b.date=:newDate"
              		+ " where b.id=:id");
        	 q.setParameter("id", id);
             q.setParameter("newStatus", newStatus);
             q.setParameter("newDate", newDate);
             q.setParameter("newAmount", newAmount);
             q.executeUpdate();
             
//             while(newAmount != em.find(Booking.class,id).getAmount());
             
//             return em.find(Booking.class,id);
             return toBeUpdated;
         }
         
         
	}

	@Override
	public void deleteById(int bookingId) {
      Query q=em.createQuery("delete from Booking as b where b.id=:id ");
      q.setParameter("id", bookingId);
      q.executeUpdate();
		
	}

}
