

package com.sancode.miniproject.dao;

import java.util.List;

import com.sancode.miniproject.domain.Booking;

public interface BookingDAO {

	public Booking findBookingById(int id);
	public List<Booking> getAllBooking();
	Booking saveBooking(Booking toBeAdded);
	public Booking updateBooking(Booking toBeUpdated,int id);
	void deleteById(int bookingId);

}
