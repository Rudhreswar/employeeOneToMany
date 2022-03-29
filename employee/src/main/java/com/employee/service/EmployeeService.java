package com.employee.service;


import com.employee.entity.EmployeeAddressEntity;
import com.employee.entity.EmployeeAttendanceEntity;
import com.employee.entity.EmployeeEntity;
import com.employee.entity.EmployeeSalaryEntity;
import com.employee.model.*;
import com.employee.repository.EmployeeAddressRepository;
import com.employee.repository.EmployeeAttendanceRepository;
import com.employee.repository.EmployeeEntityRepository;
import com.employee.repository.EmployeeSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class EmployeeService {


    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;
    @Autowired
    private EmployeeAddressRepository employeeAddressRepository;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    public void addDetails(Employee employee) {

        //Adding Details ..............................................................add ...!

        List<EmployeeAddressEntity> employeeAddressEntities = new LinkedList<>();

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeId(employee.getId());
        employeeEntity.setEmpName(employee.getEmpName());
        employeeEntity.setEmpEmail(employee.getEmpEmail());



        employee.getEmployeeAddresses().stream().forEach(employee1 ->
        {
            EmployeeAddressEntity employee2 = new EmployeeAddressEntity();
            employee2.setLine1(employee1.getLine1());
            employee2.setLine2(employee1.getLine2());
            employee2.setPhoneNumber(employee1.getPhoneNumber());
            employee2.setCity(employee1.getCity());
            employee2.setCountry(employee1.getCountry());
            employee2.setEmployeeEntity(employeeEntity);

            employeeAddressEntities.add(employeeAddressRepository.save(employee2));
        });

employeeEntity.setEmployeeAddressEntities(employeeAddressEntities);
        //................................................

        List<EmployeeAttendanceEntity> employeeAttendanceEntityList = new LinkedList<>();
        employee.getEmployeeAttendances().stream().forEach(employeeAttendance -> {
            EmployeeAttendanceEntity employeeAttendance1 = new EmployeeAttendanceEntity();
            employeeAttendance1.setDate(employeeAttendance.getDate());
            employeeAttendance1.setHoliday(employeeAttendance.isHoliday());
            employeeAttendance1.setReasonForHoliday(employeeAttendance.getReasonForHoliday());
            employeeAttendance1.setEmployeeEntity(employeeEntity);
            employeeAttendanceEntityList.add(employeeAttendanceRepository.save(employeeAttendance1));

        });
        employeeEntity.setEmployeeAttendanceEntities(employeeAttendanceEntityList);

        EmployeeSalaryEntity employeeSalaryEntity = new EmployeeSalaryEntity();
        employeeSalaryEntity.setSalary(employee.getEmployeeSalary().getSalary());
        employeeSalaryEntity.setPayable(employee.getEmployeeSalary().getPayable());
        employeeEntity.setEmployeeSalary(employeeSalaryEntity);
        employeeSalaryEntity.setEmployeeEntity(employeeEntity);

        employeeSalaryRepository.save(employeeSalaryEntity);

        employeeEntityRepository.save(employeeEntity);
    }

    public Set<Employee> getEmployeeByCountry(String country) {
        LinkedList<EmployeeAddressEntity> employeeAddressEntityLinkedList = employeeAddressRepository.findEmployeeAddressByCountry(country);
        Set<EmployeeEntity> employeeEntitySet = employeeAddressEntityLinkedList.stream()
                .map(EmployeeAddressEntity::getEmployeeEntity)
                .collect(Collectors.toSet());
        return employeeEntitySet.stream().map(this::getEmployee)
                .collect(Collectors.toSet());
    }

    public Employee getEmployee(EmployeeEntity employeeEntity) {
        Employee employeeDetails = new Employee();
        employeeDetails.setId(employeeEntity.getEmployeeId());
        employeeDetails.setEmpName(employeeEntity.getEmpName());
        employeeDetails.setEmpEmail(employeeEntity.getEmpEmail());
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setEmployeeId(employeeEntity.getEmployeeSalary().getId());
        employeeSalary.setSalary(employeeEntity.getEmployeeSalary().getSalary());
        employeeSalary.setPayable(employeeEntity.getEmployeeSalary().getPayable());
        employeeDetails.setEmployeeSalary(employeeSalary);
        List<EmployeeAddress> employeeAddresses = new LinkedList<>();
        employeeEntity.getEmployeeAddressEntities().forEach(employeeAddressE -> {
            EmployeeAddress employeeAddress = new EmployeeAddress();
            employeeAddress.setLine1(employeeAddressE.getLine1());
            employeeAddress.setLine2(employeeAddressE.getLine2());
            employeeAddress.setEmployeeId(employeeAddressE.getId());
            employeeAddress.setCity(employeeAddressE.getCity());
            employeeAddress.setCountry(employeeAddressE.getCountry());
            employeeAddress.setPhoneNumber(employeeAddressE.getPhoneNumber());
            employeeAddresses.add(employeeAddress);
        });
        employeeDetails.setEmployeeAddresses(employeeAddresses);
        List<EmployeeAttendance> employeeAttendances = new LinkedList<>();

        employeeEntity.getEmployeeAttendanceEntities().forEach(employeeAttendanceEntity -> {
            EmployeeAttendance employeeAttendance = new EmployeeAttendance();
            employeeAttendance.setEmployeeId(employeeAttendanceEntity.getId());
            employeeAttendance.setDate(employeeAttendanceEntity.getDate());
            employeeAttendance.setHoliday(employeeAttendanceEntity.isHoliday());
            employeeAttendance.setReasonForHoliday(employeeAttendanceEntity.getReasonForHoliday());
            employeeAttendances.add(employeeAttendance);
        });
        employeeDetails.setEmployeeAttendances(employeeAttendances);
        return employeeDetails;
    }

    public Set<Employee> getEmployeeByCity(String city1, String city2) {
        List<EmployeeAddressEntity> employeeAddressEntities = employeeAddressRepository.findByCityOrCity(city1, city2);
        Set<String> stringSet = employeeAddressEntities.stream()
                .map(EmployeeAddressEntity::getEmployeeEntity)
                .map(EmployeeEntity::getEmployeeId)
                .collect(Collectors.toSet());

        return stringSet.stream().map(eCity -> employeeEntityRepository.findById(eCity).orElse(null))
                .filter(Objects::nonNull)
                .map(this::getEmployee).collect(Collectors.toSet());
    }

    public Set<Employee> getEmployeeByCityAndCountry(String city, String country) {
        LinkedList<EmployeeAddressEntity> a = employeeAddressRepository.findByCityAndCountry(city, country);
        return a.stream().map(EmployeeAddressEntity::getEmployeeEntity)
                .map(this::getEmployee)
                .collect(Collectors.toSet());
    }

    public EmployeeReport getEmployeeWithSalary(EmployeeEntity employeeEntity) {
        return new EmployeeReport(
                employeeEntity.getEmployeeId(), employeeEntity.getEmpName(), employeeEntity.getEmpEmail(),
                employeeEntity.getEmployeeSalary().getSalary(), employeeEntity.getEmployeeSalary().getPayable());
    }

    public List<EmployeeReport> getEmployeeSalaryDetails() {
        List<EmployeeEntity> empDetails = employeeEntityRepository.findAll();
        return empDetails.stream().map(this::getEmployeeWithSalary)
                .collect(Collectors.toList());
    }

    public List<EmployeeReport> getEmployeeSalaryDetails(String payable) {

        LinkedList<EmployeeSalaryEntity> salaryEntityLinkedList = employeeSalaryRepository.findByPayable(payable);
        List<EmployeeEntity> emp = salaryEntityLinkedList.stream()
                .map(EmployeeSalaryEntity::getEmployeeEntity)
                .collect(Collectors.toList());

        return emp.stream().map(this::getEmployeeWithSalary)
                .collect(Collectors.toList());
    }

    public List<EmployeeHolidaysOffJan> getEmployeeNoOfDays(String date) {

        List<EmployeeAttendanceEntity> employeeAttendanceEntityStream = employeeAttendanceRepository.findAll();
        Set<String> stringSet = employeeAttendanceEntityStream.stream().filter(attendance -> attendance.getDate().contains(date))
                .map(EmployeeAttendanceEntity::getEmployeeEntity).map(EmployeeEntity::getEmployeeId)
                .collect(Collectors.toSet());

        List<Integer> size = new LinkedList<>();

        stringSet.forEach(empId -> {
            List<EmployeeAttendanceEntity> collect = employeeAttendanceEntityStream.stream().filter(EmployeeAttendanceEntity::isHoliday)
                    .filter(empAtd1 -> empAtd1.getEmployeeEntity().getEmployeeId() == (empId))
                    .collect(Collectors.toList());
            size.add(collect.size());
        });
        List<EmployeeAttendanceEntity> empAtt = employeeAttendanceEntityStream.stream()
                .filter(EmployeeAttendanceEntity::isHoliday)
                .collect(Collectors.toList());

        Set<EmployeeEntity> employeeEntitySet = stringSet.stream().map(em -> employeeEntityRepository.findById(em).orElse(null)).collect(Collectors.toSet());
        List<EmployeeHolidaysOffJan> employeeHolidaysOffJanList = new LinkedList<>();

        AtomicInteger i = new AtomicInteger();

        employeeEntitySet.forEach(employeeEntity -> {
            EmployeeHolidaysOffJan employeeHolidaysOffJanCount = new EmployeeHolidaysOffJan(
                    employeeEntity.getEmployeeId(), employeeEntity.getEmpName(),
                    employeeEntity.getEmployeeAddressEntities().stream()
                            .map(EmployeeAddressEntity::getPhoneNumber)
                            .collect(Collectors.toList()),
                    employeeEntity.getEmployeeSalary().getSalary(), size.get(i.getAndIncrement()));

            employeeHolidaysOffJanList.add(employeeHolidaysOffJanCount);
        });
        return employeeHolidaysOffJanList;

    }
}
