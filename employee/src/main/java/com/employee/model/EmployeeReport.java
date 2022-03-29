package com.employee.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeReport {
    private String employeeId;
    private String empName;
    private String empEmail;
    private double salary;
    private String payable;


}