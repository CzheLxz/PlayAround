package com.hairuo.tbk.controller;

import com.hairuo.tbk.entity.*;
import com.hairuo.tbk.repository.*;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @Description 用户控制类
 * @Author czhe
 */
@RequestMapping("/user")
@RestController
@Transactional
public class BuserController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BuserRepository buserRepository;

    /**
     * @Description 退出当前用户
     */
    @GetMapping("/removeUser")
    public Result removeUser() {
        try {
            session.removeAttribute("currentUser");
        } catch (Exception e) {
            return new Result(ResultCode.SYSTEMERROR);
        }
        return new Result(ResultCode.SUCCESS);

    }

    /**
     * @return 如果不存在返回null 否则用户标识
     * @Description 获取当前用户
     */
    @GetMapping("/currentUser")
    public Result currentUser() {
        if (session.getAttribute("currentUser") != null) {
            String userUuid = session.getAttribute("currentUser").toString();
            return new Result(ResultCode.SUCCESS, userUuid);
        }
        return new Result(ResultCode.LOGICERROR);
    }


    /**
     * @Description 用户注册
     */
    @PostMapping("/save")
    public Result save(@RequestParam("username") String username, @RequestParam("password") String password,
                       @RequestParam("phone") String phone) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(phone)) {
            return new Result(ResultCode.PARAMERROR);
        }
        Buser buser = buserRepository.getBuserByUserName(username);
        if (buser != null) {
            return new Result(ResultCode.LOGICERROR);
        }
        buser = new Buser();
        int result = buserRepository.saveBuser(username, password, phone, buser.getUserUuid());
        if (result > 0) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.SYSTEMERROR);
    }


    /**
     * @Description 根据用户名和用户密码登陆
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new Result(ResultCode.PARAMERROR);
        }
        try {
            Buser buser = buserRepository.getBuserByUserNameAndUserPassword(username, password);
            if (buser != null) {
                session.setAttribute("currentUser", buser.getUserUuid());
                return new Result(ResultCode.SUCCESS);
            } else {
                return new Result(ResultCode.LOGICERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

}

