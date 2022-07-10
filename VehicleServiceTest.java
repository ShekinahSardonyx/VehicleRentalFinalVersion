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

import com.vrm.dto.VehicleDto;
import com.vrm.entity.Vehicle;
import com.vrm.exception.VehicleNotFoundException;
import com.vrm.repository.VehicleRepository;
import com.vrm.serviceimpl.VehicleService;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class VehicleServiceTest {

	@Mock
	VehicleRepository vehicleRepository;
	
	@InjectMocks
	VehicleService vehicleService = new VehicleService();
	static Vehicle vehicle = new Vehicle();
	static VehicleDto vehicleDto;
	static List<Vehicle> vehicleList ;
	static List<Vehicle> vehicleList1;
	static List<VehicleDto> vehicleDtoList;
	
	
	@BeforeAll
	public static void setUp(){
		vehicleList = new ArrayList<Vehicle>();
		vehicleList1 = new ArrayList<Vehicle>();
		vehicleDtoList = new ArrayList<VehicleDto>();
		
		vehicleDto = new VehicleDto();
		vehicle.setVehicleId(1);
		vehicle.setVehicleNumber("AP123");
		vehicle.setType("Car");
		vehicle.setLocation("Andhra");
		vehicleList.add(vehicle);
		BeanUtils.copyProperties(vehicle, vehicleDto);
	}

	@Test
	void testAddVehicle() {
		Mockito.when(vehicleRepository.saveAndFlush(vehicle)).thenReturn(vehicle);
		assertEquals("Vehicle Added Successfully", vehicleService.addVehicle(vehicleDto));
	}
	
	@Test
	public void testVehicleNotFoundException() {
		Mockito.when(vehicleRepository.findAll()).thenReturn(vehicleList1);
		try {
			assertNotNull(vehicleService.numOfVehicles());
		}
		catch(VehicleNotFoundException ve) {
			assertEquals("Vehicle Not Found", ve.getMessage());
		}
	}

}
