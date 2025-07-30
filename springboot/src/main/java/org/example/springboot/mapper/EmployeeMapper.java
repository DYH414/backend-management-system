package org.example.springboot.mapper;

import org.example.springboot.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll();
}
