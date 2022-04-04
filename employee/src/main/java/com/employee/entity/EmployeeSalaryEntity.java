package com.employee.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Table(name = "Employee_Salary")
public class EmployeeSalaryEntity {

    @Id
    private String Id;
    private Double salary;
    private String payable;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private EmployeeEntity employeeEntitySal;
}