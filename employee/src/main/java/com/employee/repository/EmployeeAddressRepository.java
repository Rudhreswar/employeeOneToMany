package com.employee.repository;

import com.employee.entity.EmployeeAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddressEntity, String> {

    LinkedList<EmployeeAddressEntity> findEmployeeAddressByCountry(String country);

    List<EmployeeAddressEntity> findByCityOrCity(String city1, String city2);

    LinkedList<EmployeeAddressEntity> findByCityAndCountry(String city, String country);
}
