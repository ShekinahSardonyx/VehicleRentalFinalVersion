package com.vrm.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerDtoTest {

	static CustomerDto customerDto;
	
	@BeforeAll
	static void setUp() throws Exception{
		customerDto = new CustomerDto();
		
		customerDto.setCustomerFirstName("Subbu");
		customerDto.setCustomerLastName("Bobbala");
        customerDto.setCustomerEmailId("subbu77@gmail.com");
        customerDto.setCustomerMobileNumber("9666130629");
        customerDto.setCustomerAddress("Telangana");

	}

	@Test
	static void testGetters() {
		assertEquals("Subbu", customerDto.getCustomerFirstName());
		assertEquals("Bobbala", customerDto.getCustomerLastName());
		assertEquals("subbu@Gmail.com", customerDto.getCustomerEmailId());
	    assertEquals("9949293669", customerDto.getCustomerMobileNumber());
	    assertEquals("Telangana", customerDto.getCustomerAddress());
	}

}
