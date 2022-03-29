package com.employee.repository;

import com.employee.entity.EmployeeSalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryEntity, String> {

    LinkedList<EmployeeSalaryEntity> findByPayable(String payable);
}
