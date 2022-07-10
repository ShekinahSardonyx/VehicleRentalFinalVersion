package com.vrm.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrm.dto.BookingDto;
import com.vrm.entity.Booking;
import com.vrm.exception.BookingNotFoundException;

import com.vrm.repository.BookingRepository;
import com.vrm.serviceimpl.BookingService;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BookingServiceTest {
	
	@Mock
	BookingRepository bookingRepository;

	 @InjectMocks
	 BookingService bookingService=new BookingService();
	 static Booking booking=new Booking();
	 static BookingDto bookingDto;
	 static List<Booking> bookinglist;
	 static List<Booking> bookinglist1;
	 static List<BookingDto> bookingDtoList;
	 
	 
	@BeforeAll
	static void setUp() {
			bookinglist=new ArrayList<>();
			bookinglist1=new ArrayList<>();
			bookingDtoList=new ArrayList<>();
			
			
			bookingDto=new  BookingDto();
			booking.setBookingId(1234);
			booking.setDistance(50.0);
			booking.setTotalCost(500.0);
			bookinglist.add(booking);
			BeanUtils.copyProperties(booking, bookingDto);
			
		
	}

	

	@Test
	public void testaddBooking() {
		Mockito.when(bookingRepository.saveAndFlush(booking)).thenReturn(booking);
		assertNotNull(bookingService.addBooking(bookingDto));
		
	}

	@Test
	public void testViewAllBookings() throws BookingNotFoundException {
		Mockito.when(bookingRepository.findAll()).thenReturn(bookinglist);
	}
	
		@Test
    	public void testBookingNotFoundException() {
		Mockito.when(bookingRepository.findAll()).thenReturn(bookinglist1);
		try {
			assertNotNull(bookingService.viewAllBookings());
		}
		catch(BookingNotFoundException e) {
			assertEquals("Booking Not Found",e.getMessage());
		}
	}

}