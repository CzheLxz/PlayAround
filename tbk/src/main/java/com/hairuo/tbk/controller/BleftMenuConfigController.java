package com.hairuo.tbk.controller;

import com.hairuo.tbk.entity.BleftMenuConfig;
import com.hairuo.tbk.repository.BleftMenuConfigRepository;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 左侧菜单控制类
 * @Author czhe
 */

@RequestMapping("/leftMenu")
@RestController
@Transactional
public class BleftMenuConfigController {

    @Autowired
    private HttpSession session;
    @Autowired
    private BleftMenuConfigRepository bleftMenuConfigRepository;

    /**
     * @Description 根据用户标识获取左侧菜单集合
     */
    @GetMapping("/showList")
    public Result showList() {
        String userUuid = session.getAttribute("currentUser").toString();
        List<BleftMenuConfig> bleftMenuConfigList = bleftMenuConfigRepository.findAllByUserUuid(userUuid);
        return new Result(ResultCode.LOGICERROR.SUCCESS, bleftMenuConfigList);
    }

    /**
     * @Description 新增菜单
     */
    @PostMapping("/save")
    public Result save(@RequestParam("bShow") String bShow, @RequestParam("remark") String remark,
                       @RequestParam("url") String url, @RequestParam("userUuid") String userUuid) {
        if (StringUtils.isBlank(bShow) || StringUtils.isBlank(remark) || StringUtils.isBlank(url) || StringUtils.isBlank(userUuid)) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = bleftMenuConfigRepository.saveBleftMenuConfig(bShow, remark, url, userUuid);
        if (result > 0) {
            return new Result(ResultCode.SUCCESS);
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
        int result = bleftMenuConfigRepository.removeBleftMenuConfigById(id);
        if (result > 0) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

    /**
     * @Description 修改根据Id
     */
    @PostMapping("/update")
    public Result update(@RequestParam("bShow") String bShow, @RequestParam("url") String url,
                         @RequestParam("remark") String remark, @RequestParam("id") Integer id) {
        if (StringUtils.isBlank(bShow) || StringUtils.isBlank(url) || StringUtils.isBlank(remark) || id == null) {
            return new Result(ResultCode.PARAMERROR);
        }
        int result = bleftMenuConfigRepository.updateBleftMenuConfigById(bShow, url, remark, id);
        if (result > 0) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.SYSTEMERROR);
    }

}
