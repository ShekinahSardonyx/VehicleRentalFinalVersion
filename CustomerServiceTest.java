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

import com.vrm.dto.CustomerDto;
import com.vrm.entity.Customer;
import com.vrm.exception.CustomerNotFoundException;
import com.vrm.repository.CustomerRepository;
import com.vrm.serviceimpl.CustomerService;

@SpringBootTest
class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;
	
	
	@InjectMocks
	CustomerService  customerService= new CustomerService();
	static Customer customer =new Customer();
	static CustomerDto customerDto;
	static List<Customer> customerList;
	static List<Customer> customerList1;
	static List<CustomerDto> customerDtoList;
	
	@BeforeAll
	static void setUp(){
		customerList = new ArrayList<Customer>();
		customerList1= new ArrayList<Customer>();
		customerDtoList= new ArrayList<CustomerDto>();
		
		customerDto = new CustomerDto();
		customer.setCustomerFirstName("subbu");
		customer.setCustomerLastName("Bobbala");
		customer.setCustomerEmailId("subbu77@gmail.com");
		customer.setCustomerMobileNumber("7976527542");
		customer.setCustomerAddress("Telangana");
		
		BeanUtils.copyProperties(customer, customerDto);
	}


	@Test
	public void testAddCustomer() {
		Mockito.when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
		assertEquals("Customer added successfully", customerService.addCustomer(customerDto));
	}
	
	@Test
	public void testViewAllCustomer() throws CustomerNotFoundException{
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		assertNotNull(customerService.viewAllCustomer());
	}
	
	@Test
	public void testCustomerNotFoundException() {
		Mockito.when(customerRepository.findAll()).thenReturn(customerList1);
		try {
			assertNotNull(customerService.viewAllCustomer());
		}
		catch(CustomerNotFoundException cu) {
			assertEquals("Customer Not Found", cu.getMessage());
		}
	}

}
