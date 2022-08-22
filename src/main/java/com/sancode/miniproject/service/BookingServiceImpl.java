

package com.sancode.miniproject.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sancode.miniproject.dao.BookingDAO;
import com.sancode.miniproject.domain.Booking;
import com.sancode.miniproject.domain.BookingEquipment;
import com.sancode.miniproject.domain.Client;
import com.sancode.miniproject.domain.Equipment;
import com.sancode.miniproject.domain.Layout;
import com.sancode.miniproject.domain.MailRequest;
import com.sancode.miniproject.domain.Room;

@Service
public class BookingServiceImpl implements BookingService {

	
	BookingDAO dao;
	EquipmentService equipmentService;
	ClientService clientService;
	RoomService roomService;
	LayoutService layoutService;
	
	@Autowired
	EmailService service;
	
	@Autowired
	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	@Autowired
	public void setDao(BookingDAO dao) {
		this.dao = dao;
	}
	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	@Autowired
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	@Autowired
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	@Override
	public List<Booking> getAllBookings() {
         
		return dao.getAllBooking();
	}

	/* creates a new booking
	 * Sends request to DAO class to add that booking
	 */
	@Override
	public Booking addNewBooking(Booking toBeAdded) {
		Booking newBooking=new Booking();
		if(isBookingValid(toBeAdded.getRoom().getRoomId(), toBeAdded.getDuration(), toBeAdded.getDurationRange(), toBeAdded.getDate(),0) == false) {
			System.out.println(toBeAdded.getRoom().getRoomId() + toBeAdded.getDuration() + toBeAdded.getDurationRange() + toBeAdded.getDate());
			throw new IllegalArgumentException("Cannot book this room as this room is booked by another client.");
		}
		Client newClient = toBeAdded.getClient();
		if(newClient.getClientId() == 0) {
			int newClientId = clientService.addNewClient(newClient);
			newBooking.setClient(clientService.findById(newClientId));
		}
		else {
			newBooking.setClient(toBeAdded.getClient());
		}
		Room newRoom = roomService.findById(toBeAdded.getRoom().getRoomId());
		newBooking.setRoom(newRoom);
		Layout newLayout = layoutService.findById(toBeAdded.getLayout().getLayoutId());
		newBooking.setLayout(newLayout);
		newBooking.setDate(toBeAdded.getDate());
		newBooking.setDeposit(toBeAdded.getDeposit());
		newBooking.setDuration(toBeAdded.getDuration());
		newBooking.setDurationRange(toBeAdded.getDurationRange());
		newBooking.setAttendees(toBeAdded.getAttendees());
		newBooking.setPaymentMethod(toBeAdded.getPaymentMethod());
		newBooking.setRegistrationDate(toBeAdded.getRegistrationDate());
//		newBooking.setStatus(toBeAdded.getStatus());
		newBooking.setStatus("Confirmed");
		newBooking.setLayout(toBeAdded.getLayout());
		newBooking.setAmount(toBeAdded.getAmount());
		
		
		for(BookingEquipment current : toBeAdded.getRequiredEquipments()) {
			Equipment newEquipment = equipmentService.findById(current.getEquipment().getId());
			BookingEquipment newBookingEquipment = new BookingEquipment();
			newBookingEquipment.setEquipment(newEquipment);
			newBookingEquipment.setUnits(current.getUnits());
			
			newBooking.addBookingEquipment(newBookingEquipment);
		}
		Booking object=dao.saveBooking(newBooking);
		/*
		 * sending mail after successful made booking with all details
		 */
		Map<String,Object>model=new HashMap<>();
		model.put("room",newRoom.getName());
		model.put("layout",newLayout.getName());
		model.put("date", toBeAdded.getDate());
		model.put("deposit", toBeAdded.getDeposit());
		String durationRange=toBeAdded.getDurationRange();
		durationRange=durationRange.replace("#", "&");
		model.put("duration_range",durationRange);
		model.put("attendees", toBeAdded.getAttendees());
		model.put("payment_method", toBeAdded.getPaymentMethod());
		model.put("amount", toBeAdded.getAmount());
		model.put("status", toBeAdded.getStatus());
	
		
		
		MailRequest request=new MailRequest();
		request.setTo(newBooking.getClient().getEmail());
		request.setFrom("roombookingapp5@gmail.com");
		request.setSubject("Booking confirmation");
		model.put("message","Congratulation! Your Booking has been confirmed");
		service.sendEmail(request, model);
		return object;
				
	}

	public boolean isBookingValid(int roomId, String duration, String durationRange, String date, int id) {
		List<Booking> bookingList = dao.getAllBooking();
		
		Predicate<Booking> byRoomId = booking -> booking.getRoom().getRoomId() == roomId;
		Predicate<Booking> byDate = booking -> booking.getDate().compareTo(date) == 0;
		Predicate<Booking> byStatus = booking -> booking.getStatus().compareTo("Confirmed") == 0;
		Predicate<Booking> byBookingId = booking -> booking.getId() != id;
		List<Booking> filteredList = bookingList.stream().filter(byRoomId).filter(byDate).filter(byStatus).filter(byBookingId).collect(Collectors.toList());
		
//		System.out.println("in is booking valid:" + filteredList.size());
		
		for(Booking currentBooking : filteredList) {
			if(currentBooking.getDuration().compareTo("fullday") == 0 || duration.compareTo("fullday") == 0) {
				return false;
			}
			if(isTimeConflicting(currentBooking.getDurationRange(), durationRange)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isTimeConflicting(String durationRange1, String durationRange2) {
		String[] d1Array = durationRange1.split("#", 13);
		String[] d2Array = durationRange2.split("#", 13);
		
		for(String slot1 : d1Array) {
			for(String slot2 : d2Array) {
				String[] slot1Array = slot1.split(" - ", 13);
				String[] slot2Array = slot2.split(" - ", 13);
				if((slot2Array[0].compareTo(slot1Array[0]) >= 0 && slot2Array[0].compareTo(slot1Array[1]) < 0) 
						|| (slot1Array[0].compareTo(slot2Array[0]) >= 0 && slot1Array[0].compareTo(slot2Array[1]) < 0) ) {
					
					return true;
					
				}
			}
		}
		
		return false;
	}
	
	@Override
	public Booking updateBooking(Booking toBeUpdated, int id) {
		if(dao.findBookingById(id) != null) {
			Booking oldBooking = dao.findBookingById(id);
			
			String finalStatus = toBeUpdated.getStatus() != null ? toBeUpdated.getStatus() : oldBooking.getStatus();
			String finalDate = toBeUpdated.getDate() != null ? toBeUpdated.getDate() : oldBooking.getDate();
			float finalAmount = toBeUpdated.getAmount() == 0 ? oldBooking.getAmount() : toBeUpdated.getAmount();
			
			if( (finalAmount < 0) || (finalAmount > 1000000) ) {
				throw new IllegalArgumentException("Please choose the amount in the range $0 to $1000000");
			}
			
			if(checkDate(finalDate).compareTo("Success") != 0) {
				throw new IllegalArgumentException(checkDate(finalDate));
			}
			
			if(finalStatus.compareTo("Confirmed") == 0) {
				if( isBookingValid(oldBooking.getRoom().getRoomId(), oldBooking.getDuration(), oldBooking.getDurationRange(), finalDate, id) == false) {
					throw new IllegalArgumentException("Cannot confirm the booking as it conflicts with other bookings");
				}
			}
			
			oldBooking.setStatus(finalStatus);
			oldBooking.setAmount(finalAmount);
			oldBooking.setDate(finalDate);
			dao.saveBooking(oldBooking);
			/*
			 * sending mail after updating booking with all details
			 */
			Map<String,Object>model=new HashMap<>();
			model.put("room",oldBooking.getRoom().getName());
			model.put("layout",oldBooking.getLayout().getName());
			model.put("date", oldBooking.getDate());
			model.put("deposit", oldBooking.getDeposit());
			String durationRange=oldBooking.getDurationRange();
			durationRange=durationRange.replace("#", "&");
			model.put("duration_range",durationRange);
			model.put("attendees", oldBooking.getAttendees());
			model.put("payment_method", oldBooking.getPaymentMethod());
			model.put("amount", oldBooking.getAmount());
			model.put("status", oldBooking.getStatus());
			
			
			MailRequest request=new MailRequest();
			request.setTo(oldBooking.getClient().getEmail());
			request.setFrom("roombookingapp5@gmail.com");
			request.setSubject("Booking Update");
			model.put("message","Your Booking has been updated, Please Contact admin for more details");
			service.sendEmail(request, model);
			return oldBooking;
		}
		else {
			throw new IllegalArgumentException("There is no such booking");
		}
	}
	
	private String checkDate(String date) {
		
		String[] arrOfStr = date.split("-", 4);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
//            System.out.println(dateFormat.parse(date));
        } catch (ParseException pe) {
            return "Please enter a valid date (dd-mm-yyyy)";
        }
		
//		//check if only 3 fields are there
//		if(arrOfStr.length != 3) {
//			return false;
//		}
//		
//		//check if each field is an integer
//		try{
//			Integer.parseInt(arrOfStr[0]);
//			Integer.parseInt(arrOfStr[1]);
//			Integer.parseInt(arrOfStr[2]);
//		}catch(NumberFormatException e) {
//			return false;
//		}
//		
//		if( (Integer.parseInt(arrOfStr[0]) == 29) && () )
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		//check if the date is greater than today's date
		if(compareDate(date) == false) {
			return "Cannot accept past dates";
		}
		
		int bDay = Integer.parseInt(arrOfStr[0]);
		int bMonth = Integer.parseInt(arrOfStr[1]);
		int bYear = Integer.parseInt(arrOfStr[2]);
		
		if(bYear > year + 1) {
			return "Please choose a date within one year";
		}
		
		else if(bYear == year) {
			return "Success";
		}
		
		else if(bMonth < month) {
			return "Success";
		}
		
		else if(bMonth > month) {
			return "Please choose a date within one year";
		}
		
		else if(bDay <= day) {
			return "Success";
		}
		
		return "Please choose a date within one year";
	}

	@Override
	public String removeBooking(int bookingId) {
		if(dao.findBookingById(bookingId) != null) {
			/*
			 * sending mail after deleting a booking with all details
			 */
			Booking oldBooking=dao.findBookingById(bookingId);
			Map<String,Object>model=new HashMap<>();
			model.put("room",oldBooking.getRoom().getName());
			model.put("layout",oldBooking.getLayout().getName());
			model.put("date", oldBooking.getDate());
			model.put("deposit", oldBooking.getDeposit());
			String durationRange=oldBooking.getDurationRange();
			durationRange=durationRange.replace("#", "&");
			model.put("duration_range",durationRange);
			model.put("attendees", oldBooking.getAttendees());
			model.put("payment_method", oldBooking.getPaymentMethod());
			model.put("amount", oldBooking.getAmount());
			model.put("status", "Canceled");
			
			
			
			MailRequest request=new MailRequest();
			request.setTo(oldBooking.getClient().getEmail());
			request.setFrom("roombookingapp5@gmail.com");
			request.setSubject("Booking Cancellation");
			model.put("message","Your Booking has been canceled,Please Contact admin for more details");
			service.sendEmail(request, model);
			dao.deleteById(bookingId);
			return "Success";
		}
		else {
			throw new IllegalArgumentException("There is no such booking");
		}
		
	}

	@Override
	public Booking findBookingById(int id) {

		Booking booking = dao.findBookingById(id);
		if(booking != null) {
			return booking;
		}
		else {
			throw new IllegalArgumentException("Booking not found");
		}
	}
	@Override
	public List<Booking> getAllBookingsByRoomId(int roomId) {
		List<Booking> bookingList = dao.getAllBooking();
		
		Predicate<Booking> byRoomId = booking -> booking.getRoom().getRoomId() == roomId;
		Predicate<Booking> byDate = booking -> compareDate(booking.getDate());
		Predicate<Booking> byStatus = booking -> booking.getStatus().compareTo("Confirmed") == 0;
		return bookingList.stream().filter(byRoomId).filter(byDate).filter(byStatus).collect(Collectors.toList());
	}
	
	
	public boolean compareDate(String date) {

//		Calendar calobj = Calendar.getInstance();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		System.out.println(day + " " +  month + " " + year);
		
		String[] arrOfStr = date.split("-", 4);
		
		
		if(year < Integer.parseInt(arrOfStr[2])) {
			return true;
		}
		else if(year > Integer.parseInt(arrOfStr[2])) {
			return false;
		}
		else if(month < Integer.parseInt(arrOfStr[1])) {
			return true;
		}
		else if(month > Integer.parseInt(arrOfStr[1])) {
			return false;
		}
		else if(day > Integer.parseInt(arrOfStr[0])) {
			return false;
		}
		else{
			return true;
		}
	}

}