package com.vrm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrm.dto.DriverDto;
import com.vrm.entity.Driver;
import com.vrm.exception.DriverNotFoundException;
import com.vrm.repository.DriverRepository;
import com.vrm.service.IDriverService;
@Service
public class DriverService implements IDriverService{

	@Autowired
	public DriverRepository driverRepository;
	
	static Logger logger=LoggerFactory.getLogger(DriverService.class.getClass());
	
	public DriverDto addDriver(DriverDto driverDto) {
		
		Driver driver  = new Driver();
		BeanUtils.copyProperties(driverDto, driver);
		driverRepository.save(driver);
		logger.info("Driver service impl addDriver : {}");
		return driverDto;
	}
	
	public List<DriverDto> removeDriver(int id){
		
		driverRepository.deleteById(id);
		List<Driver> driverList = driverRepository.findAll();
		List<DriverDto> driverDtoList = new ArrayList<>();
		DriverDto driverDto = new DriverDto();
		for(Driver driver : driverList) {
			BeanUtils.copyProperties(driver, driverDto);
			driverDtoList.add(driverDto);
		}
		logger.info("Driver Service impl removeDriver : {}");
		return driverDtoList;
		
	}
	
	public DriverDto updateDriver(int id, String mobNum) throws DriverNotFoundException{
		Driver driver = new Driver();
		DriverDto driverDto = new DriverDto();
		if(driverRepository.findById(id).get()==null) {
			throw new DriverNotFoundException();
		}
		else {
			BeanUtils.copyProperties(driverRepository.findById(id).get(), driverDto);
			driverDto.setDriverMobileNumber(mobNum);
			BeanUtils.copyProperties(driverDto, driver);
			driverRepository.saveAndFlush(driver);
			return driverDto;
		}
	}
	
	public String AcceptOrRejectBooking(String message) {
		if(message.equals("Accept")) {
			return "Booking Accepted";
		}
		else {
			return "Booking Rejected";
		}
	}
	
}
