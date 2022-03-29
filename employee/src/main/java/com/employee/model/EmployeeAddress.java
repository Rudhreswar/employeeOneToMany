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


public class EmployeeAddress {

    private String employeeId;
    private String line1;
    private String line2;
    private Long phoneNumber;
    private String city;
    private String country;
}