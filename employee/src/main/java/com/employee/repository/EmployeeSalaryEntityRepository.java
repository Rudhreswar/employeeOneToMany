package com.employee.repository;

import com.employee.entity.EmployeeSalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface EmployeeSalaryEntityRepository extends JpaRepository<EmployeeSalaryEntity, String> {

    LinkedList<EmployeeSalaryEntity> findByPayable(String payable);
}
