package org.example.springboot.controller;

import com.github.pagehelper.PageInfo;
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

    //增加
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee) {
        employeeService.add(employee);
        return Result.success();
    }

    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return Result.success();
    }

    //更新
    @PutMapping("/update")
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }

    //查询所有
    @GetMapping("/selectAll")
    public Result selectAll(Employee employee) {
        List<Employee> list = employeeService.selectAll(employee);
        return Result.success(list);
    }

    //查询单个
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }

    //   分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Employee> pageInfo = employeeService.selectPage(employee,pageNum, pageSize);
        return Result.success(pageInfo);
    }


}
