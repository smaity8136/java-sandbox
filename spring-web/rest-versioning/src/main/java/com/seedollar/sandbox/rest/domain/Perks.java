package com.seedollar.sandbox.rest.domain;

public interface Perks {

    default Double calculateEmployeeDiscountV1(Employee employee) {
        return employee.getAge() < 10 ? 3.0d : 5.0d;
    }

    default Double calculateEmployeeDiscountV2(Employee employee) {
        return (employee.getAge() > 10 && employee.isParkingPermitted()) ? 2.5d : 4.5d;
    }
}
