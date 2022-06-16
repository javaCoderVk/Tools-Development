package com.vk.demo.objectCreation;

public class Address {
	private String street;
	private String streetNo;
	 public Address() {
		super();
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", streetNo=" + streetNo + "]";
	}

}
