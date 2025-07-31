package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.Employee;
import org.example.springboot.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public void add(Employee employee) {
        employeeMapper.insert(employee);
    }

    public List<Employee> selectAll() {
       List<Employee>list= employeeMapper.selectAll();
       return list;
    }


    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
     }


    public PageInfo<Employee> selectPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
       List<Employee>list= employeeMapper.selectAll();
       return PageInfo.of(list);

    }


}
