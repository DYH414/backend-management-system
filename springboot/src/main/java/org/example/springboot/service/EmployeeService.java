package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.Employee;
import org.example.springboot.exception.CustomException;
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

    public List<Employee> selectAll(Employee employee) {
        List<Employee> list = employeeMapper.selectAll(employee);
        return list;
    }


    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }


    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);

    }


    public void update(Employee employee) {
        employeeMapper.updateById(employee);

    }

    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    public void  deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public Employee login(Employee employee) {
        String username = employee.getUsername();
        Employee dbEmployee=employeeMapper.selectByUsername(username);
        if (dbEmployee==null)
        {
            throw new CustomException("500","用户名不存在");
        }

        String password = employee.getPassword();
        if(!dbEmployee.getPassword().equals(password))
            throw new CustomException("500","账号或密码错误");
        return dbEmployee;
    }
}
