package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Employee;
import org.example.springboot.exception.CustomException;
import org.example.springboot.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    @Resource
    private EmployeeService employeeService;
    @GetMapping("/hello")
    public Result index() {
        return Result.success("Hello, Wo111rld!");
    }
    @PostMapping("/login")
    public Result login(@RequestBody Employee employee) {
        Employee dbEmployee=employeeService.login(employee);
        return Result.success(dbEmployee);

    }
}
