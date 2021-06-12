package com.microservice.employee.service;

import com.microservice.employee.VO.Department;
import com.microservice.employee.VO.ResponseTemplateVO;
import com.microservice.employee.entity.Employee;
import com.microservice.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveEmployee(Employee employee) {
        log.info("Inside saveEmployee of Employee");

        employeeRepository.save(employee);
    }

    public ResponseTemplateVO getEmployeeWithDepartment(Long employeeId) {
        log.info("Inside getEmployeeWithDepartment of EmployeeService");

        ResponseTemplateVO vo = new ResponseTemplateVO();
        Employee employee = employeeRepository.findEmployeeByEmployeeId(employeeId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" +
                employee.getDepartmentID(), Department.class);

        vo.setEmployee(employee);
        vo.setDepartment(department);

        return vo;
    }
}
