package org.example.springboot.service;

import jakarta.annotation.Resource;
import org.example.springboot.entity.Employee;
import org.example.springboot.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    public List<Employee> selectAll() {
       List<Employee>list= employeeMapper.selectAll();
       return list;
    }
}
