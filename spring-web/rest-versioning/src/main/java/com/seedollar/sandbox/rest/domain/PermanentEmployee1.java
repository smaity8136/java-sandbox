package com.seedollar.sandbox.rest.domain;

public class PermanentEmployee1 extends Employee {

	private String sapNumber;

	private Employee manager;

	public PermanentEmployee1(String employeeNumber, String firstName, String lastName, int age, boolean parkingPermitted, Employee manager, String sapNumber) {
		super(employeeNumber, firstName, lastName, age, parkingPermitted);
		this.manager = manager;
		this.sapNumber = sapNumber;
	}

	public String getSapNumber() {
		return sapNumber;
	}

	public void setSapNumber(String sapNumber) {
		this.sapNumber = sapNumber;
	}
}
