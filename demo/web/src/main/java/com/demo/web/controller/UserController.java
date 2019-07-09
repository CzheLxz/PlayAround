package com.demo.web.controller;

import com.demo.dao.entity.User;
import com.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/6/20 15:12
 * @description 用户控制器
 **/
@Api(tags = "用户接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public String add(@RequestBody User user) {
        int result = userService.insert(user);
        return result > 0 ? "ok" : "false";
    }

    @ApiOperation(value = "打招呼", notes = "根据姓名和年龄打招呼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "path"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "path")
    })
    @PostMapping("/hello/{name}/{age}")
    public String hello(@PathVariable String name, @PathVariable String age) {
        return "hello " + name + "," + age + " year old handsome guy";
    }


    public static void main(String[] args) {
        System.out.println(Calculation(5));
    }

    public static int Calculation(int n) {
        if (n <= 1) {
            return n;
        }
        return n + Calculation(n - 1);
    }

}
