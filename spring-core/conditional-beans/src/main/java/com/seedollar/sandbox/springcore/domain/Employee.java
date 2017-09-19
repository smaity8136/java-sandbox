package com.seedollar.sandbox.springcore.domain;

public class Employee {

    private String employeeNumber;

    private Double salary;

    public Employee(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        this.salary = 18000.00;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}
