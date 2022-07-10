package com.vrm.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentDtoTest {

	static PaymentDto paymentDto ;
	@BeforeAll
	static void setUp() throws Exception {
		paymentDto = new PaymentDto();
		
		paymentDto.setPaymentId(1);
		paymentDto.setPaymentMode("debit");
	}

	@Test
	void testGetters() {
		assertEquals(1,paymentDto.getPaymentId());
		assertEquals("debit", paymentDto.getPaymentMode());
		
	}

}
