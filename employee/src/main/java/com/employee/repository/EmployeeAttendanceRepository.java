package com.employee.repository;

import com.employee.entity.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, String> {

}
