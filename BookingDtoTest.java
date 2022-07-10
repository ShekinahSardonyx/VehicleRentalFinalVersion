package com.vrm.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingDtoTest {

	static BookingDto bookingDto;
	
	@BeforeAll
	static void setUp(){
		bookingDto=new BookingDto();
		bookingDto.setBookingId(1234);
		bookingDto.setBookingDescription("Car NEON 15");
		bookingDto.setDistance(50.0);
	}

	@Test
	void testGetters() {
		double epsilon = 0.000001d;
		assertEquals( 1234 ,bookingDto.getBookingId());
		assertEquals("Car NEON 15", bookingDto.getBookingDescription());
		assertEquals( 50.0 ,bookingDto.getDistance(), epsilon);
	}

}
