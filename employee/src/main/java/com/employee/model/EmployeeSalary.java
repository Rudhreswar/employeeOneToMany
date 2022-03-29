package com.employee.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeSalary {

    private String employeeId;
    private double salary;
    private String payable;


}