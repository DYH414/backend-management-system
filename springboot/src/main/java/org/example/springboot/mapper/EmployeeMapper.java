package org.example.springboot.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll(Employee employee);
    @Select("select *from employee where id=#{id}")
    Employee  selectById(Integer id);

    void insert(Employee employee);

    void updateById(Employee employee);

    @Delete("delete from employee where id=#{id}")
    void deleteById(Integer id);
}
