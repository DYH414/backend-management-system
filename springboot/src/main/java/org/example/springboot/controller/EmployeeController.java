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
    public Result add(@RequestBody Employee employee)
    {
      employeeService.add(employee);
      return Result.success();
    }

//查询所有
    @GetMapping("/selectAll")
    public Result selectAll()
    {
        List<Employee>list=employeeService.selectAll();
        return Result.success(list);
    }
//查询单个
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id)
    {
        Employee employee=employeeService.selectById(id);
        return Result.success(employee);
    }

//   分页查询
@GetMapping("/selectPage")
public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize)
{
    PageInfo<Employee>pageInfo =employeeService.selectPage(pageNum, pageSize);
    return Result.success(pageInfo);
}


}
