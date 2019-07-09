package com.hairuo.tbk.controller;

import com.hairuo.tbk.entity.BuserConfig;
import com.hairuo.tbk.repository.BuserConfigRepository;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description 用户配置控制类
 */
@RequestMapping("/userConfig")
@RestController
@Transactional
public class BuserConfigController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BuserConfigRepository buserConfigRepository;


    /**
     * @Description 查询用户配置信息
     */
    @GetMapping("/find")
    public Result find() {
        BuserConfig buserConfig = buserConfigRepository.getBuserConfigByUserUuid(session.getAttribute("currentUser").toString());
        if (buserConfig != null) {
            return new Result(ResultCode.SUCCESS, buserConfig);
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 如果已经配置则修改否则新增
     */
    @PostMapping("/update")
    public Result update(@RequestParam("appKey") String appKey, @RequestParam("secretKey") String secretKey,
                         @RequestParam("userUrl") String userUrl, @RequestParam("user_uuid") String user_uuid) {
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(secretKey) || StringUtils.isBlank(userUrl) || StringUtils.isBlank(user_uuid)) {
            return new Result(ResultCode.PARAMERROR);
        }
        try {
            BuserConfig buserConfig = buserConfigRepository.getBuserConfigByUserUuid(user_uuid);
            int result = 0;
            if (buserConfig != null) {
                result = buserConfigRepository.updateBuserConfigByUserUuid(appKey, secretKey, userUrl, user_uuid);
            } else {
                result = buserConfigRepository.saveBuserConfig(appKey, secretKey, userUrl, user_uuid);
            }
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

}
