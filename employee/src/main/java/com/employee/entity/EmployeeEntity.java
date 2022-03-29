package com.employee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id

    @Column(name = "employeeId_pk")
    private String employeeId;
    @Column(name = "EmployeeName")
    private String empName;
    @Column(name = "Employee_Email")
    private String empEmail;


    @OneToMany(/*targetEntity = EmployeeAddressEntity.class,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "Emp_Addresses_Fk", referencedColumnName = "employeeId_pk")
    private List<EmployeeAddressEntity> employeeAddressEntities;

    @OneToMany(/*targetEntity = EmployeeAttendanceEntity.class,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "Emp_Attendance_FK" /*, referencedColumnName = "employeeId_pk"*/)
    private List<EmployeeAttendanceEntity> employeeAttendanceEntities;

    @OneToOne(/*targetEntity = EmployeeSalaryEntity.class,*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "Emp_sal_Fk"/*, referencedColumnName = "employeeId"*/)
    private EmployeeSalaryEntity employeeSalary;

}
