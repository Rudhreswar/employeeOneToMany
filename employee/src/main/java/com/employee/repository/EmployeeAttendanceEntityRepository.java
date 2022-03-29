package com.employee.repository;

import com.employee.entity.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttendanceEntityRepository extends JpaRepository<EmployeeAttendanceEntity, String> {

}
