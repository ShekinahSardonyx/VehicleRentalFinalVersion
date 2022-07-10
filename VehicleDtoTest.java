package com.vrm.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VehicleDtoTest {

	static VehicleDto vehicleDto;
	
	@BeforeAll
	static void setUp() throws Exception {
		
		vehicleDto = new VehicleDto();
		vehicleDto.setVehicleId(1);
		vehicleDto.setVehicleNumber("AP123");
		vehicleDto.setType("Car");
		vehicleDto.setLocation("Andhra");
	}
	

	@Test
	void testGetters() {
		assertEquals(1, vehicleDto.getVehicleId());
		assertEquals("AP123", vehicleDto.getVehicleNumber());
		assertEquals("Car", vehicleDto.getType());
		assertEquals("Andhra", vehicleDto.getLocation());
	}

}
