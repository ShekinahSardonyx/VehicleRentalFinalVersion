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

import com.vrm.dto.DriverDto;
import com.vrm.entity.Driver;
import com.vrm.exception.DriverNotFoundException;
import com.vrm.repository.DriverRepository;
import com.vrm.serviceimpl.DriverService;



@SpringBootTest
class DriverServiceTest {

	@Mock
	DriverRepository driverRepository;
	
	@InjectMocks
	DriverService driverService = new DriverService();
	static Driver driver = new Driver();
	static DriverDto driverDto;
	static List<Driver>driverList;
	static List<Driver> driverList1;
	
	static List<DriverDto>driverDtoList;
	
	
	@BeforeAll
	public static void setUp() {
		driverList = new ArrayList<Driver>();
		driverList1 = new ArrayList<Driver>();
		driverDtoList = new ArrayList <DriverDto>();
	          
		driverDto=new DriverDto();
		driver.setDriverFirstName("joe");
		driver.setDriverFirstName("jill");
		driver.setDriverAddress("Hyderabad");
		driver.setDriverMobileNumber("9812345674");
		        
		driver.setDriverEmailId("abd@gmail.com");
	    driverList.add(driver);
	BeanUtils.copyProperties(driver,driverDto);
	
	}

	@Test
	void testAddDriver() {
		Mockito.when(driverRepository.saveAndFlush(driver)).thenReturn(driver);
	    assertEquals(driverDto, driverService.addDriver(driverDto));
	}
	
	
	@Test
	public void testdriverNotFoundException() {
		Mockito.when(driverRepository.findAll()).thenReturn(driverList);
		try {
			assertNotNull(driverService.addDriver(driverDto));
		}
		catch(DriverNotFoundException e) {
		
			assertEquals("Driver Not Found",e.getMessage());
		
		}
	
	}


	
	
}