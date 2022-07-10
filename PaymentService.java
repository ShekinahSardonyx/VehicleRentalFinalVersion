package com.vrm.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrm.dto.PaymentDto;
import com.vrm.entity.Payment;
import com.vrm.exception.PaymentNotFoundException;
import com.vrm.repository.PaymentRepository;
import com.vrm.service.IPaymentService;

@Service
public class PaymentService implements IPaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private VehicleService vehicleService;
	
	static Logger logger=LoggerFactory.getLogger(PaymentService.class.getClass());
	
	public String addPayment(PaymentDto paymentDto)
	{
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentDto, payment);
		paymentRepository.save(payment);
		logger.info("Payment service impl addPayment: {}");
		return"payment added succesfully";
		
	}
	public String cancelPayment(int id) {
		
		paymentRepository.deleteById(id);
		logger.info("Payment Service impl cancelPayment : {}");
		return "payment cancelled sucessfully";
	}
	
	public PaymentDto updatePaymentMode(int id, String paymentMode) throws PaymentNotFoundException{
		Payment payment = new Payment();
		PaymentDto paymentDto = new PaymentDto();
		if(paymentRepository.findById(id).get()==null) {
			throw new PaymentNotFoundException();
		}
		else {
			BeanUtils.copyProperties(paymentRepository.findById(id).get(), paymentDto);
			paymentDto.setPaymentMode(paymentMode);
			BeanUtils.copyProperties(paymentDto, payment);
			paymentRepository.flush();
			return paymentDto;
		}
	}
	
	public PaymentDto updatePaymentDate(int id, LocalDate paymentDate) throws PaymentNotFoundException{
		Payment payment= new Payment();
		PaymentDto paymentDto = new PaymentDto();
		if(paymentRepository.findById(id).get()==null) {
			throw new PaymentNotFoundException();
		}
		else {
			BeanUtils.copyProperties(paymentRepository.findById(id).get(), paymentDto);
			paymentDto.setPaymentDate(paymentDate);
			BeanUtils.copyProperties(paymentDto, payment);
			paymentRepository.saveAndFlush(payment);
			return paymentDto;
		}
	}
	
	/*
	 * public PaymentDto updatePaymentStatus(int id, String paymentStatus) { Payment
	 * payment = new Payment(); PaymentDto paymentDto = new PaymentDto();
	 * if(paymentRepository.findById(id).get()==null) { throw new
	 * PaymentNotFoundException(); } else {
	 * BeanUtils.copyProperties(paymentRepository.findById(id).get(), paymentDto);
	 * paymentDto.setPaymentStatus(paymentStatus);
	 * BeanUtils.copyProperties(paymentDto, payment); paymentRepository.flush();
	 * return paymentDto; }
	 * 
	 * }
	 */
	
	public PaymentDto viewpayment( int id ) throws PaymentNotFoundException{
		if(paymentRepository.findById(id).get()==null) {
			throw new PaymentNotFoundException();
		}
		else {
			Payment payment = paymentRepository.findById(id).get();	 
			PaymentDto paymentDto = new PaymentDto();
			BeanUtils.copyProperties(payment, paymentDto);
			return paymentDto;
		}
	}
	
	public List<PaymentDto> viewAllPayments () throws PaymentNotFoundException{
		if(paymentRepository.findAll()==null) {
			throw new PaymentNotFoundException();
		}
		else {
			List<Payment> paymentList= paymentRepository.findAll();
			List<PaymentDto> paymentDtoList = new ArrayList<>();
			PaymentDto paymentDto = new PaymentDto();
			for(Payment payment: paymentList) {
				BeanUtils.copyProperties(payment, paymentDto);
				paymentDtoList.add(paymentDto);
			}
			return paymentDtoList;
		}
	}
	
	public String getpaymentStatus(int paymentId) {
		
		String message ="";
		if(paymentRepository.existsById(paymentId)) {
			message ="Payment Done Successfully";
		}
		else {
			message="Payment Failed";
		}
		paymentRepository.getByPaymentId(paymentId).setPaymentStatus(message);
		
		return message;
	}
	
	public double calculateMonthlyPayment(String type, String role, LocalDate d1, LocalDate d2) {
		
		double payment1=0.0;
		
		if(type.equals("Car")) {
			double totalPayment = vehicleService.fixedChargesForCar(d1, d2);
			if(role.equals("Admin")) {
				payment1 = totalPayment * 0.75;
			}
			else if(role.equals("Driver")) {
				payment1 = totalPayment * 0.25;
			}
		}
		else if(type.equals("Bike")) {
			double totalPayment = vehicleService.fixedChargesForBike(d1, d2);
			
			if(role.equals("Admin")) {
				payment1 = totalPayment * 0.75;
			}
			else if(role.equals("Driver")) {
				payment1 = totalPayment * 0.25;
			}
		}
		
		else if(type.equals("Bicycle")) {
			double totalPayment = vehicleService.fixedChargesForBicycle(d1, d2);
			
			if(role.equals("Admin")) {
				payment1 = totalPayment * 0.75;
			}
			else if(role.equals("Driver")) {
				payment1 = totalPayment * 0.25;
			}
		}
		return payment1;	
	}

	public double CalculateTotalPayment(String type, LocalDate d1, LocalDate d2) {
		double totalPayment=0.0;
		if(type.equals("Car")) {
			totalPayment = vehicleService.fixedChargesForCar(d1, d2);
		}
		else if(type.equals("Bike")) {
			totalPayment = vehicleService.fixedChargesForBike(d1, d2);
		}
		else if(type.equals("Bicycle")) {
			totalPayment = vehicleService.fixedChargesForBicycle(d1, d2);
		}
		return totalPayment;
	}
		
}

	