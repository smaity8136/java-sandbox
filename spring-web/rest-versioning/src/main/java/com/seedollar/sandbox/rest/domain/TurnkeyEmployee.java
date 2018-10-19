package com.seedollar.sandbox.rest.domain;

import java.time.LocalDate;

public class TurnkeyEmployee extends Employee implements Perks {

	private boolean intranetAccess;

	private Double employeeDiscount;

	public TurnkeyEmployee(String employeeNumber, String firstName, String lastName, int age, boolean parkingPermitted) {
		super(employeeNumber, firstName, lastName, age, parkingPermitted);
	}

	public boolean isIntranetAccess() {
		return intranetAccess;
	}

	public void setIntranetAccess(boolean intranetAccess) {
		this.intranetAccess = intranetAccess;
	}

	public Double getEmployeeDiscount() {
		return employeeDiscount;
	}

	public void setEmployeeDiscount(Double employeeDiscount) {
		this.employeeDiscount = employeeDiscount;
	}
}
