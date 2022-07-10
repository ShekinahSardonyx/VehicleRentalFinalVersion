package com.vrm.serviceimpl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrm.dto.CustomerDto;
import com.vrm.entity.Customer;
import com.vrm.exception.CustomerNotFoundException;
import com.vrm.repository.CustomerRepository;
import com.vrm.service.ICustomerService;
@Service
public class CustomerService implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	static Logger logger=LoggerFactory.getLogger(CustomerService.class.getClass());
	
	
	
	public String addCustomer(CustomerDto customerDto) {
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		customerRepository.save(customer);
		logger.info("Customer service impl addCustomer : {}");
		return "Customer added successfully";
		
	}
	
	public List<CustomerDto> removeCustomer(int id) throws CustomerNotFoundException{
		
		customerRepository.deleteById(id);
		CustomerDto customerDto = new CustomerDto();
		if(customerRepository.findAll()==null) {
			throw new CustomerNotFoundException();
		}
		else {
			List<Customer> customerList = customerRepository.findAll();
			List<CustomerDto> customerDtoList = new ArrayList<>();
			for(Customer customer : customerList) {
				BeanUtils.copyProperties(customer, customerDto);
				customerDtoList.add(customerDto);
			}
			logger.info("Customer Service impl deleteCustomer : {}");
			return customerDtoList;
		}
		
	}
	public CustomerDto viewCustomer(int id)  throws CustomerNotFoundException{
		if(customerRepository.findById(id).get()==null) {
			throw new CustomerNotFoundException();
		}
		else {
			Customer customer = customerRepository.findById(id).get();
			CustomerDto customerDto = new CustomerDto();
			BeanUtils.copyProperties(customer, customerDto);
			return customerDto;
		}
	}
	public CustomerDto updateCustomer(int id,String mobNum)  throws CustomerNotFoundException{
		Customer customer= new Customer();
		CustomerDto customerDto = new CustomerDto();
		if(customerRepository.findById(id).get()==null) {
			throw new CustomerNotFoundException();
		}
		else {
			BeanUtils.copyProperties(customerRepository.findById(id).get(), customerDto);
			customerDto.setCustomerMobileNumber(mobNum);
			BeanUtils.copyProperties(customerDto, customer);
			customerRepository.saveAndFlush(customer);
			return customerDto;
		}
	}
	public List<CustomerDto> viewAllCustomer()  throws CustomerNotFoundException{
		if(customerRepository.findAll()==null) {
			throw new CustomerNotFoundException();
		}
		else {
			List<Customer> customerList = customerRepository.findAll();
			List<CustomerDto> customerDtoList = new ArrayList<>();
			CustomerDto customerDto = new CustomerDto();
			for(Customer customer : customerList) {
				BeanUtils.copyProperties(customer, customerDto);
				customerDtoList.add(customerDto);
			}
			return customerDtoList;
		}
		
	}
	public List<CustomerDto> viewAllCustomersByLocation(String location)  throws CustomerNotFoundException{
		if(customerRepository.getByCustomerAddress(location)==null) {
			throw new CustomerNotFoundException();
		}
		else {
			List<Customer> customerList =  customerRepository.getByCustomerAddress(location) ;
			List<CustomerDto> customerDtoList = new ArrayList<>();
			CustomerDto customerDto = new CustomerDto();
			for(Customer customer : customerList) {
				BeanUtils.copyProperties(customer, customerDto);
				customerDtoList.add(customerDto);
			}
			return customerDtoList;
		}
	}
	
	
}
