package com.vrm.service;

import java.time.LocalDate;
import java.util.List;

import com.vrm.dto.BookingDto;
import com.vrm.dto.VehicleDto;

public interface IBookingService {

	 public BookingDto addBooking(BookingDto bookingDto);
	 public BookingDto updateBookingFromDate(int id,LocalDate date);
	 public BookingDto updateBookingTillDate(int id,LocalDate tilldate);
	 public BookingDto updatedistance(int id,double distance);
	 public BookingDto updatecost(int id,double cost);
	 public String cancelBooking(int id);
	 public BookingDto viewBooking(int id);
	 public List<BookingDto> viewAllBookings();
	 public List<BookingDto> viewAllBookingByDate(LocalDate bdate);
	 public List<VehicleDto> getAllBookedVehicles();
	 public List<VehicleDto> getAllAvailableVehicles();
	 public int numOfVehiclesRented();
}
