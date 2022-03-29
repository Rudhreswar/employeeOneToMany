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


public class EmployeeDetails {

    private Employee employees;
    private EmployeeAddress employeeAddress;
    private List<EmployeeAttendance> employeeAttendanceList;
    private EmployeeSalary employeeSalary;

}