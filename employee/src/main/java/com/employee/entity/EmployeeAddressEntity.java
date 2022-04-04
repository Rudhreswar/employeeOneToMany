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

@Table(name = "Employee_Addresses")
public class EmployeeAddressEntity {
    @Id
    private String Id;
    @Column(name = "Address_line1")
    private String line1;
    @Column(name = "Address_line2")
    private String line2;
    @Column(name = "Phone_Number")
    private Long phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "Country")
    private String country;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private EmployeeEntity employeeEntityAddress;

}
