package com.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeAttendance {

    private String employeeId;
    private String date;
    private boolean holiday;
    private String reasonForHoliday;

}