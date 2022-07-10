package com.vrm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DriverDto extends UserDto{

	 @NotNull(message="Please enter First Name")
	 private String driverFirstName;
	 
	 private String driverLastName;
	 
	 private String driverAddress;
	 
	 private String driverMobileNumber;
	 
	 @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	 private String driverEmailId;
	 
	 @NotNull(message="Please enter LicenseNo")
	 private String driverLicenseNo;
	 
	 public DriverDto() {}

	public DriverDto(String driverFirstName, String driverLastName, String driverAddress,
			String driverMobileNumber, String driverEmailId, String driverLicenseNo) {
		super();
		
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.driverAddress = driverAddress;
		this.driverMobileNumber = driverMobileNumber;
		this.driverEmailId = driverEmailId;
		this.driverLicenseNo = driverLicenseNo;
	}

	

	public String getDriverFirstName() {
		return driverFirstName;
	}

	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	public String getDriverLastName() {
		return driverLastName;
	}

	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	public String getDriverAddress() {
		return driverAddress;
	}

	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}

	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}

	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}

	public String getDriverEmailId() {
		return driverEmailId;
	}

	public void setDriverEmailId(String driverEmailId) {
		this.driverEmailId = driverEmailId;
	}

	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}

	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}

	@Override
	public String toString() {
		return "DriverDto [driverFirstName=" + driverFirstName + ", driverLastName=" + driverLastName
				+ ", driverAddress=" + driverAddress + ", driverMobileNumber=" + driverMobileNumber + ", driverEmailId="
				+ driverEmailId + ", driverLicenseNo=" + driverLicenseNo + "]";
	}

	
	
	

}
