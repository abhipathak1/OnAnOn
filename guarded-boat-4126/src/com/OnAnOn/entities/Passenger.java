package com.OnAnOn.entities;

import java.io.Serializable;

public class Passenger implements Serializable {
	private String firstName;
	private String lastName;
	private String address;
	private String mobileNo;
	private String username;
	private String password;

	public Passenger(String firstName, String lastName, String address, String mobileNo, String username,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNo = mobileNo;
		this.username = username;
		this.password = password;
	}

	// Getters and Setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Passenger{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", address='"
				+ address + '\'' + ", mobileNo='" + mobileNo + '\'' + ", username='" + username + '\'' + ", password='"
				+ password + '\'' + '}';
	}
}
