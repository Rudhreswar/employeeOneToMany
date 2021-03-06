package com.employee.controller;

import com.employee.model.Employee;
import com.employee.model.EmployeeHolidaysOffJan;
import com.employee.model.EmployeeReport;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/employee")

public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Employee employee) {
        employeeService.addDetails(employee);
    }

    @RequestMapping(value = "/country/{country}", method = RequestMethod.GET)
    private Set<Employee> get(@PathVariable String country) {
        return employeeService.getEmployeeByCountry(country);

    }

    @RequestMapping(value = "/city/{city1}/{city2}", method = RequestMethod.GET)
    private Set<Employee> getEmployeeByCity(@PathVariable String city1, @PathVariable String city2) {
        return employeeService.getEmployeeByCity(city1, city2);

    }

    @RequestMapping(value = "/city/{city}/{country}", method = RequestMethod.GET)
    private Set<Employee> getEmployeeByCityAndCountry(@PathVariable String city, @PathVariable String country) {
        return employeeService.getEmployeeByCityAndCountry(city, country);

    }

    @RequestMapping(value = "/salary/details", method = RequestMethod.GET)
    private List<EmployeeReport> getEmployeeSalary() {
        return employeeService.getEmployeeSalaryDetails();

    }

    @RequestMapping(value = "/payable/details/{payable}", method = RequestMethod.GET)
    private List<EmployeeReport> getEmployeeSalary(@PathVariable String payable) {
        return employeeService.getEmployeeSalaryDetails(payable);

    }

    @RequestMapping(value = "/holiday/NoOfDays/{date}", method = RequestMethod.GET)
    private List<EmployeeHolidaysOffJan> getEmployeeNoOfDays(@PathVariable String date) {
        return employeeService.getEmployeeNoOfDays(date);

    }


}