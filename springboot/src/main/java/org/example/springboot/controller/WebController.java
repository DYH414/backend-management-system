package org.example.springboot.controller;

import org.example.springboot.common.Result;
import org.example.springboot.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {
    @GetMapping("/hello")
    public Result index() {
        return Result.success("Hello, Wo111rld!");
    }

    @GetMapping("/count")
    public Result count() {

        throw new CustomException("0001", "自定义异常");
    }

    @GetMapping("/map")
    public Result map() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        return Result.success(map);
    }
}
