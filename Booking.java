package com.vrm.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="customerBooking")
	private Customer customer;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicleBooking")
	private Vehicle vehicle;
	
	private LocalDate bookingFromDate;
	
	private LocalDate bookedTillDate;
	@Column
	private String bookingDescription;
	
	@Column
	private double totalCost;
	
	
	private double distance;
	
    public Booking(){}

	public Booking( Customer customer, Vehicle vehicle, LocalDate bookingFromDate, LocalDate bookedTillDate,
			String bookingDescription, double totalCost, double distance) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.bookingFromDate = bookingFromDate;
		this.bookedTillDate = bookedTillDate;
		this.bookingDescription = bookingDescription;
		this.totalCost = totalCost;
		this.distance = distance;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getBookingDate() {
		return bookingFromDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingFromDate = bookingDate;
	}

	public LocalDate getBookedTillDate() {
		return bookedTillDate;
	}

	public void setBookedTillDate(LocalDate bookedTillDate) {
		this.bookedTillDate = bookedTillDate;
	}

	public String getBookingDescription() {
		return bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customer=" + customer + ", vehicle=" + vehicle + ", bookingFromDate="
				+ bookingFromDate + ", bookedTillDate=" + bookedTillDate + ", bookingDescription=" + bookingDescription
				+ ", totalCost=" + totalCost + ", distance=" + distance + "]";
	}
       
}