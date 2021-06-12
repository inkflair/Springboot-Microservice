package com.microservice.employee.controller;

import com.microservice.employee.VO.ResponseTemplateVO;
import com.microservice.employee.entity.Employee;
import com.microservice.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public void saveEmployee(@RequestBody Employee employee) {
        log.info("Inside saveEmployee of EmployeeController");
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getEmployeeWithDepartment(@PathVariable("id") Long employeeId) {
        log.info("Inside getEmployeeWithDepartment of EmployeeController");
        return employeeService.getEmployeeWithDepartment(employeeId);

    }
}

