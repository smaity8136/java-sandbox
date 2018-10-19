package com.seedollar.sandbox.rest.domain;

import java.time.LocalDate;

public class PermanentEmployee2 extends PermanentEmployee1 {

	private boolean overtime;

	public PermanentEmployee2(String employeeNumber, String firstName, String lastName, int age, boolean parkingPermitted, Employee manager, String sapNumber, boolean overtime) {
		super(employeeNumber, firstName, lastName, age, parkingPermitted, manager, sapNumber);
		this.overtime = overtime;
	}

	public boolean isOvertime() {
		return overtime;
	}

	public void setOvertime(boolean overtime) {
		this.overtime = overtime;
	}
}
