package org.example.springboot.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll();
    @Select("select *from employee where id=#{id}")
    Employee  selectById(Integer id);
}
