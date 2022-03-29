package com.employee.model;


// //6) Retrieve employees employee_id, name, phone_number, Salary, no of days off for the month of Jan 2021........................


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeHolidaysOffJan {
    private String empId;
    private String name;
    private List<Long> phoneNumber1;
    private double salary;
    private Integer noOfDaysOff;

}