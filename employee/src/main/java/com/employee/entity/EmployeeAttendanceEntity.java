package com.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity

@Table(name = "Employee_Attendance")
public class EmployeeAttendanceEntity {
    @Id
    private String Id;
    private String date;
    private boolean holiday;
    private String reasonForHoliday;

    @ManyToOne(cascade = CascadeType.ALL)
    private EmployeeEntity employeeEntity;

}
