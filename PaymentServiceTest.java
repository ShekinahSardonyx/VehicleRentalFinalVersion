package com.vrm.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vrm.dto.PaymentDto;
import com.vrm.entity.Payment;
import com.vrm.exception.PaymentNotFoundException;
import com.vrm.repository.PaymentRepository;
import com.vrm.serviceimpl.PaymentService;


@SpringBootTest
class PaymentServiceTest {

	@Mock
	PaymentRepository paymentRepository;
	
	@InjectMocks
	PaymentService paymentService = new PaymentService();
	
	static Payment payment =new Payment();
	static PaymentDto paymentDto;
	static List<Payment> paymentList;
	static List<Payment> paymentList1;
	static List<PaymentDto> paymentDtoList;
	
	@BeforeAll
	public static void setUp(){
		paymentList = new ArrayList<Payment>();
		paymentList1 = new ArrayList<Payment>();
		paymentDtoList = new ArrayList<PaymentDto>();
		paymentDto = new PaymentDto();
		payment.setPaymentId(1);
		payment.setPaymentMode("Cash");
		payment.setPaymentStatus("Payment done");
	}

	@Test
	public void testAddPayment() {
		Mockito.when(paymentRepository.saveAndFlush(payment)).thenReturn(payment);
		assertEquals("payment added succesfully",paymentService.addPayment(paymentDto));
	}
	
	@Test
	public void testViewAllPayments() {
		Mockito.when(paymentRepository.findAll()).thenReturn(paymentList);
		assertNotNull(paymentService.addPayment(paymentDto));
	}
	
	
	@Test
	public void testPaymentNotFoundException(){
		Mockito.when(paymentRepository.findAll()).thenReturn(paymentList);
		try {
			assertNotNull(paymentService.addPayment(paymentDto));
		}
		catch(PaymentNotFoundException pe){
			assertEquals("Payment Not Found", pe.getMessage());
		}
	}

}
