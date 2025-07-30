package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Employee;
import org.example.springboot.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
@Resource
private EmployeeService employeeService;

    @GetMapping("/selectAll")
    public Result selectAll()
    {
        List<Employee>list=employeeService.selectAll();
        return Result.success(list);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id)
    {
        Employee employee=employeeService.selectById(id);
        return Result.success(employee);
    }

    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam Integer id,@RequestParam String name)
    {
        Employee employee=employeeService.selectById(id);
        return Result.success(employee);
    }
}
