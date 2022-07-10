package com.vrm.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DriverDtoTest {

	static DriverDto driverDto;
	
	@BeforeAll
	static void setUp() throws Exception {
		driverDto = new DriverDto();
		driverDto.setDriverFirstName("john");
		driverDto.setDriverLastName("jose");
		driverDto.setDriverAddress("Hyderabad");
		driverDto.setDriverMobileNumber("9234567891");
		driverDto.setDriverLicenseNo("1234567891234");
		
	}

	@Test
	void testGetters() {
		
		assertEquals("john",driverDto.getDriverFirstName());
		assertEquals("jose",driverDto.getDriverLastName());
		assertEquals("Hyderabad",driverDto.getDriverAddress());
		assertEquals("9234567891",driverDto.getDriverMobileNumber());
		assertEquals("1234567891234",driverDto.getDriverLicenseNo());
	}

}
