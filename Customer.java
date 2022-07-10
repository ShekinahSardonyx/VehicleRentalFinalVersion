package com.vrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Customer extends User{
	
	
	
	public String customerFirstName;
	@Column
	public String customerLastName;
	
	public String customerEmailId;
	@Column
	public String customerMobileNumber;
	
	@Column
	public String customerAddress;

	public Customer() {}
	public  Customer(String customerFirstName, String customerLastName, String customerEmailId, String customerMobileNumber, String customerAddress) {
		
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmailId = customerEmailId;
		this.customerMobileNumber = customerMobileNumber;
		this.customerAddress = customerAddress;
	}
	
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}
	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	@Override
	public String toString() {
		return "Customer [customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName
				+ ", customerEmailId=" + customerEmailId + ", customerMobileNumber=" + customerMobileNumber
				+ ", customerAddress=" + customerAddress + "]";
	}
	
	
	

}