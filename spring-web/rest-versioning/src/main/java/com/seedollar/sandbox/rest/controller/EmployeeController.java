package com.seedollar.sandbox.rest.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.seedollar.sandbox.rest.domain.Employee;
import com.seedollar.sandbox.rest.domain.PermanentEmployee1;
import com.seedollar.sandbox.rest.domain.PermanentEmployee2;
import com.seedollar.sandbox.rest.domain.TurnkeyEmployee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private Employee pcPrincipal = new Employee(
			UUID.randomUUID().toString(),
			"PC",
			"Principal",
			36,
			true
			);

	private Employee peterGriffin = new Employee(
			UUID.randomUUID().toString(),
			"Peter",
			"Griffin",
			43,
			false
	);

	@GetMapping(path = "/v1/permanent")
	public List<Employee> getPermanentStaffVersion1() {
		return Lists.newArrayList(
				new PermanentEmployee1(
						UUID.randomUUID().toString(),
						"Kyle",
						"Brathloski",
						11,
						false,
						pcPrincipal,
						"497331"),
				new PermanentEmployee1(
						UUID.randomUUID().toString(),
						"Stan",
						"Marsh",
						12,
						true,
						pcPrincipal,
						"97923"),
				new PermanentEmployee1(
						UUID.randomUUID().toString(),
						"Eric",
						"Cartman",
						12,
						false,
						pcPrincipal,
						"679734")
				);
	}

	@GetMapping(path = "/v2/permanent")
	public List<Employee> getPermanentStaffVersion2() {
		return Lists.newArrayList(
				new PermanentEmployee2(
						UUID.randomUUID().toString(),
						"Kyle",
						"Brathloski",
						11,
						false,
						pcPrincipal,
						"497321", true),
				new PermanentEmployee2(
						UUID.randomUUID().toString(),
						"Stan",
						"Marsh",
						12,
						true,
						pcPrincipal,
						"625778", true),
				new PermanentEmployee2(
						UUID.randomUUID().toString(),
						"Eric",
						"Cartman",
						12,
						false,
						pcPrincipal,
						"609360", false),
				new PermanentEmployee2(
						UUID.randomUUID().toString(),
						"Kenny",
						"Mccormick",
						13,
						true,
						pcPrincipal,
						"285765", false)
				);
	}

	@GetMapping(path = "turnkey", params = "version=1")
	public List<Employee> getTurnKeyStaffVersion1() {
		TurnkeyEmployee turnkeyEmployee1 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Stewie", "Griffin", 2, true);
		TurnkeyEmployee turnkeyEmployee2 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Meg", "Griffin", 17, true);
		TurnkeyEmployee turnkeyEmployee3 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Brian", "Griffin", 5, true);

		return Stream.of(turnkeyEmployee1, turnkeyEmployee2, turnkeyEmployee3)
				.map(turnkeyEmployee -> {
					turnkeyEmployee.setEmployeeDiscount(turnkeyEmployee.calculateEmployeeDiscountV1(turnkeyEmployee));
					return turnkeyEmployee;
				}).collect(Collectors.toList());
	}

	@GetMapping(path = "turnkey", params = "version=2")
	public List<Employee> getTurnKeyStaffVersion2() {
		TurnkeyEmployee turnkeyEmployee1 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Stewie", "Griffin", 2, true);
		TurnkeyEmployee turnkeyEmployee2 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Meg", "Griffin", 17, true);
		TurnkeyEmployee turnkeyEmployee3 = new TurnkeyEmployee(
				UUID.randomUUID().toString(),
				"Brian", "Griffin", 5, true);

		return Stream.of(turnkeyEmployee1, turnkeyEmployee2, turnkeyEmployee3)
				.map(turnkeyEmployee -> {
					turnkeyEmployee.setEmployeeDiscount(turnkeyEmployee.calculateEmployeeDiscountV2(turnkeyEmployee));
					return turnkeyEmployee;
				}).collect(Collectors.toList());
	}

	@GetMapping(path = "all", headers = "X-API-VERSION=1")
	public List<Employee> allEmployeesVersion1() {
		PermanentEmployee1 permanent1 = new PermanentEmployee1(UUID.randomUUID().toString(), "Louise", "Griffin", 41, false, peterGriffin, "864863");
		PermanentEmployee2 permanent2 = new PermanentEmployee2(UUID.randomUUID().toString(), "Strong", "Woman", 33, false, pcPrincipal, "577211", true);
		TurnkeyEmployee turnkeyEmployee = new TurnkeyEmployee(UUID.randomUUID().toString(), "Bart", "Simpson", 12, false);
		return Lists.newArrayList(permanent1, permanent2, turnkeyEmployee);
	}

	@GetMapping(path = "all", headers = "X-API-VERSION=2")
	public List<Employee> allEmployeesVersion2() {
		throw new RuntimeException("All employees are not available!");
	}
}
