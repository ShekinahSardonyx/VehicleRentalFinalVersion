package com.vrm.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vrm.service.IPaymentService;

@RestController
public class AdminDriverController {

	static Logger logger=LoggerFactory.getLogger(AdminDriverController.class.getClass());
	
	@Autowired
	public IPaymentService paymentService;
	
	@GetMapping("/calcPayByRole/{type}/{role}/{date1}/{date2}")
	public double calculatePayByRole(@PathVariable("type") String type, @PathVariable("role") String role, @RequestParam(value="date1") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date1, @RequestParam(value="date2") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date2) {
		return paymentService.calculateMonthlyPayment(type, role, date1, date2);
	}
	
	
}
