package com.vrm.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrm.dto.BookingDto;
import com.vrm.dto.VehicleDto;
import com.vrm.entity.Booking;
import com.vrm.entity.Vehicle;
import com.vrm.exception.BookingNotFoundException;
import com.vrm.repository.BookingRepository;
import com.vrm.repository.VehicleRepository;
import com.vrm.service.IBookingService;

@Service
public class BookingService implements IBookingService{
	
  @Autowired
  private BookingRepository  bookingRepository; 
  
  @Autowired
  private VehicleRepository vehicleRepository;
	  
  static Logger logger=LoggerFactory.getLogger(BookingService.class.getClass());
 
  
		     public BookingDto addBooking(BookingDto bookingDto) 
		     {
		    	 Booking booking = new Booking();
		    	 BeanUtils.copyProperties(bookingDto, booking);
		    	 bookingRepository.save(booking);
		    	 logger.info("Booking added : "+bookingDto);
		    	 return bookingDto;
		   	           
		     }
			
			    
		    public BookingDto updateBookingFromDate(int id,LocalDate date) throws BookingNotFoundException
		    {
		    	   Booking booking= new Booking();
		    	   BookingDto bookingDto = new BookingDto();
		    	   if(bookingRepository.getByBookingId(id)== null) {
		    		   throw new BookingNotFoundException();
		    	   }
		    	   else {
			    	   BeanUtils.copyProperties(bookingRepository.getByBookingId(id), bookingDto);
			    	   bookingDto.setBookingDate(date);
			    	   BeanUtils.copyProperties(bookingDto, booking);
			    	   bookingRepository.saveAndFlush(booking);
			    	   logger.info("Booking Updated");
			    	   return bookingDto;
		    	   }
		          
		    }
		       
		       
		    public BookingDto updateBookingTillDate(int id,LocalDate tilldate) throws BookingNotFoundException
		    {
		    	   Booking booking= new Booking();
		    	   BookingDto bookingDto = new BookingDto();
		    	   if(bookingRepository.getByBookingId(id)== null) {
		    		   throw new BookingNotFoundException();
		    	   }
		    	   else {
			    	   BeanUtils.copyProperties(bookingRepository.getByBookingId(id), bookingDto);
			    	   bookingDto.setBookedTillDate(tilldate);
			    	   BeanUtils.copyProperties(bookingDto, booking);
			    	   bookingRepository.saveAndFlush(booking);
			    	   logger.info("Booking Updated");
			    	   return bookingDto;
		    	   }
		           
		    }
		       
		       
		    public BookingDto updatedistance(int id,double distance) throws BookingNotFoundException
		    {
		    	   Booking booking= new Booking();
		    	   BookingDto bookingDto = new BookingDto();
		    	   if(bookingRepository.getByBookingId(id)== null) {
		    		   throw new BookingNotFoundException();
		    	   }
		    	   else {
			    	   BeanUtils.copyProperties(bookingRepository.getByBookingId(id), bookingDto);
			    	   bookingDto.setDistance(distance);
			    	   BeanUtils.copyProperties(bookingDto, booking);
			    	   bookingRepository.saveAndFlush(booking);
			    	   return bookingDto;
		    	   }
		          
		    }
		       
		       
		    public BookingDto updatecost(int id,double cost) throws BookingNotFoundException
		    {
		    	   Booking booking= new Booking();
		    	   BookingDto bookingDto = new BookingDto();
		    	   if(bookingRepository.getByBookingId(id)== null) {
		    		   throw new BookingNotFoundException();
		    	   }
		    	   else {
			    	   BeanUtils.copyProperties(bookingRepository.getByBookingId(id), bookingDto);
			    	   bookingDto.setTotalCost(cost);
			    	   BeanUtils.copyProperties(bookingDto, booking);
			    	   bookingRepository.saveAndFlush(booking);
			    	   return bookingDto;
		    	   }
		           
		    }
		       
			
		       
			public String cancelBooking(int id)
			{
				
				bookingRepository.deleteById(id);
				logger.info("Cancel Booking : {} ");
				return " Booking cancelled Successfully";
			}
		
		       
			public BookingDto viewBooking(int id) throws BookingNotFoundException
			{       
			
				if(bookingRepository.getByBookingId(id)== null) {
		    		   throw new BookingNotFoundException();
		    	   }
				else {
					Booking booking = bookingRepository.getByBookingId(id);
					BookingDto bookingDto = new BookingDto();
					BeanUtils.copyProperties(booking, bookingDto);
					return bookingDto;
				}
				
			}
			
			
			public List<BookingDto> viewAllBookings() throws BookingNotFoundException
			{
				BookingDto bookingDto = new BookingDto();
				List<BookingDto> bookingDtoList = new ArrayList<>();
				if(bookingRepository.findAll()==null) {
					 throw new BookingNotFoundException();
				}
				else {
					List<Booking> bookingList = bookingRepository.findAll();
					for(Booking booking : bookingList) {
						BeanUtils.copyProperties(booking, bookingDto);
						bookingDtoList.add(bookingDto);
					}
					return bookingDtoList;
				}
			}
		
			
			public List<BookingDto> viewAllBookingByDate(LocalDate bdate) throws BookingNotFoundException
			{
				if(bookingRepository.getByBookingFromDate(bdate)==null) {
					throw new BookingNotFoundException();
				}
				else {
					List<Booking> bookingList = bookingRepository.getByBookingFromDate(bdate);
					List<BookingDto> bookingDtoList = new ArrayList<>();
					BookingDto bookingDto = new BookingDto();
					for(Booking booking : bookingList) {
						BeanUtils.copyProperties(booking, bookingDto);
						bookingDtoList.add(bookingDto);
					}
					return bookingDtoList;
				}
			}
			
		public List<VehicleDto> getAllBookedVehicles(){
				List<Booking> bookingList = bookingRepository.findAll();
				List<Vehicle> vehicleList =bookingList.stream().map(v->v.getVehicle()).collect(Collectors.toList());
				List<VehicleDto> vehicleDtoList = new ArrayList<>();
				VehicleDto vehicleDto = new VehicleDto();
				for(Vehicle vehicle : vehicleList) {
					BeanUtils.copyProperties(vehicle, vehicleDto);
					vehicleDtoList.add(vehicleDto);
				}
				return vehicleDtoList;
		}
		
		public List<VehicleDto> getAllAvailableVehicles(){
			List<Vehicle> allVehiclesList = vehicleRepository.findAll();
			List<Booking> bookingList = bookingRepository.findAll();
			List<Vehicle> availableList = new ArrayList<>();
			List<Vehicle> bookedVehicleList =bookingList.stream().map(v->v.getVehicle()).collect(Collectors.toList());
			List<Integer> vehicleIdList = bookedVehicleList.stream().map(v->v.getVehicleId()).collect(Collectors.toList());
			
			//List<Vehicle> vehicleAvailableList = allVehiclesList.stream().filter(v->v.getVehicleId()!=for(int id :: vehicleIdList).collect(Collectors.toList());
			for(int i=0; i<allVehiclesList.size(); i++) {
				for(int j=0; j<vehicleIdList.size(); j++) {
					if(allVehiclesList.get(i).getVehicleId()!=vehicleIdList.get(i)) {
						availableList.add(allVehiclesList.get(i));
					}
				}
			}
			List<VehicleDto> vehicleDtoList = new ArrayList<>();
			VehicleDto vehicleDto = new VehicleDto();
			for(Vehicle vehicle : availableList) {
				BeanUtils.copyProperties(vehicle, vehicleDto);
				vehicleDtoList.add(vehicleDto);
			}
			return vehicleDtoList;
		}
		
		public int numOfVehiclesRented() {
			List<Booking> bookingList = bookingRepository.findAll();
			List<Vehicle> vehicleList =bookingList.stream().map(v->v.getVehicle()).collect(Collectors.toList());
			int numOfVehiclesRented = vehicleList.size();
			return numOfVehiclesRented;
		}
}
