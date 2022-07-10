package com.vrm.dto;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDtoTest {

	static UserDto userDto;
	
	@BeforeAll
	static void setUp() throws Exception {
		userDto = new UserDto();
		userDto.setUserId(1);
		userDto.setPassword("Capgemini");
		userDto.setRole("Customer");
	}

	@Test
	void test() {
		assertEquals(1, userDto.getUserId());
		assertEquals("Capgemini", userDto.getPassword());
		assertEquals("Customer", userDto.getRole());
	}

}
