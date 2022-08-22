

package com.sancode.miniproject.service;

import java.util.List;

import com.sancode.miniproject.domain.Booking;

public interface BookingService {
    public Booking findBookingById(int id);
	public List<Booking> getAllBookings();
	Booking addNewBooking(Booking toBeAdded);
	public Booking updateBooking(Booking toBeUpdated,int id);
	String removeBooking(int bookingId);
	public List<Booking> getAllBookingsByRoomId(int roomId);
}
