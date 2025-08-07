package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Account;
import org.example.springboot.entity.Admin;
import org.example.springboot.entity.Employee;
import org.example.springboot.exception.CustomException;
import org.example.springboot.service.AdminService;
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
    @Resource
    private AdminService adminService;
    @GetMapping("/hello")
    public Result index() {
        return Result.success("Hello, Wo111rld!");
    }

    //管理员/员工登录
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account result;
        if ("ADMIN".equals(account.getRole()))
        {
            result=adminService.login(account);
        }
        else if ("EMP".equals(account.getRole()))
        {
            result=employeeService.login(account);
        }
        else
        {
            return Result.error("500","用户角色不存在");
        }
        return Result.success(result);
    }
@PostMapping("/register")
    public Result register(@RequestBody Employee employee) {
      employeeService.register(employee);
       return Result.success();
    }
}
