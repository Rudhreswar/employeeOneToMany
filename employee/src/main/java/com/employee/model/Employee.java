package com.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Employee {
    private String Id;
    private String empName;
    private String empEmail;
    private EmployeeSalary employeeSalary;
    private List<EmployeeAddress> employeeAddresses;
    private List<EmployeeAttendance> employeeAttendances;

}