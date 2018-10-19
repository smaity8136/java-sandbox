package com.seedollar.sandbox.rest.domain;

import java.time.LocalDate;

public class Employee {

	private String employeeNumber;

	private String firstName;

	private String lastName;

	private int age;

	private boolean parkingPermitted;

	public Employee(String employeeNumber, String firstName, String lastName, int age, boolean parkingPermitted) {
		this.employeeNumber = employeeNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.parkingPermitted = parkingPermitted;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isParkingPermitted() {
		return parkingPermitted;
	}

	public void setParkingPermitted(boolean parkingPermitted) {
		this.parkingPermitted = parkingPermitted;
	}
}
