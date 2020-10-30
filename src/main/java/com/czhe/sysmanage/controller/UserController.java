package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.paramCheck.ParamCheck;
import com.czhe.sysmanage.retrunHandle.BizException;
import com.czhe.sysmanage.retrunHandle.ResultBody;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/test")
    public boolean test() {
        System.out.println("开始...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/testNull")
    public boolean testNull() {
        System.out.println("开始...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @PostMapping("/testBizException")
    public boolean testBizException() {

        System.out.println("开始...");
        //如果姓名为空就手动抛出一个自定义的异常！
        String userName = null;
        if (userName == null) {
            throw new BizException("-1", "用户姓名不能为空！");
        }
        return true;
    }

    @GetMapping("/testSuccess")
    public ResultBody testSuccess() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        return ResultBody.success(map);
    }

    @RequestMapping("/testError")
    public ResultBody testError() {
        return ResultBody.error("099", "错误错误错误");
    }

    @GetMapping("/hello")
    public ResultBody hello(@ParamCheck(defaultValue = "阿三") @RequestParam("username") String name) {
        return ResultBody.success(name);
    }

}
 
 
 
 