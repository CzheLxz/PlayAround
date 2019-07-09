package com.hairuo.tbk.controller;


import com.alibaba.fastjson.JSON;
import com.hairuo.tbk.entity.BtopMenuConfig;
import com.hairuo.tbk.repository.BtopMenuConfigRepository;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 头部菜单控制类
 * @Author czhe
 */
@RequestMapping("/topMenu")
@RestController
@Transactional
public class BtopMenuConfigController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BtopMenuConfigRepository btopMenuConfigRepository;

    /**
     * @Description 根据用户标识获取头部菜单信息集合
     */
    @GetMapping("/showList")
    public Result showList() {
        List<BtopMenuConfig> btopMenuConfigList = null;
        try {
            String userUuid = session.getAttribute("currentUser").toString();
            btopMenuConfigList = btopMenuConfigRepository.findAllByUserUuid(userUuid);
            return new Result(ResultCode.SUCCESS, btopMenuConfigList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 新增菜单信息
     */
    @PostMapping("/save")
    public Result save(@RequestParam("bShow") String bShow, @RequestParam("remark") String remark,
                       @RequestParam("url") String url, @RequestParam("userUuid") String userUuid) {
        if (StringUtils.isBlank(bShow) || StringUtils.isBlank(remark) || StringUtils.isBlank(url) || StringUtils.isBlank(userUuid)) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = 0;
        try {
            result = btopMenuConfigRepository.saveBtopMenuConfig(bShow, remark, url, userUuid);
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 删除
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam("id") Integer id) {
        if (id == null) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = 0;
        try {
            result = btopMenuConfigRepository.removeBtopMenuConfigById(id);
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description
     */
    @PostMapping("/update")
    public Result update(@RequestParam("bShow") String bShow, @RequestParam("url") String url,
                         @RequestParam("remark") String remark, @RequestParam("id") Integer id) {
        if (StringUtils.isBlank(bShow) || StringUtils.isBlank(remark) || StringUtils.isBlank(url) || id == null) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = 0;
        try {
            result = btopMenuConfigRepository.updateBtopMenuConfigById(bShow, url, remark, id);
            if (result > 0) {
                return new Result(ResultCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

}
