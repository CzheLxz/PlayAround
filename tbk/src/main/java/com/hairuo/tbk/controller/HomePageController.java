package com.hairuo.tbk.controller;

import com.alibaba.fastjson.JSONObject;
import com.hairuo.tbk.entity.*;
import com.hairuo.tbk.repository.*;
import com.hairuo.tbk.pojo.RequestObject;
import com.hairuo.tbk.pojo.Result;
import com.hairuo.tbk.pojo.ResultCode;
import com.hairuo.tbk.service.BuserConfigService;
import com.hairuo.tbk.service.TbkService;
import com.hairuo.tbk.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 商城首页控制类
 * @Author czhe
 */

@RequestMapping("/tbk/homePage")
@RestController
@Transactional
@CrossOrigin
public class HomePageController {

    @Autowired
    private BtopMenuConfigRepository btopMenuConfigRepository;

    @Autowired
    private BleftMenuConfigRepository bleftMenuConfigRepository;

    @Autowired
    private BphotoConfigRepository bphotoConfigRepository;

    @Autowired
    private BshopConfigRepository bshopConfigRepository;

    @Autowired
    private BuserConfigService buserConfigService;

    @Autowired
    private BfriendLinkRepository bfriendLinkRepository;

    @Autowired
    private TbkService tbkService;

    //首页头部菜单
    @PostMapping("/topMenu")
    public Result topMenu(@RequestParam("url") String url) {
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userUuid = buserConfig.getBuser().getUserUuid();
        List<BtopMenuConfig> btopMenuConfigList = btopMenuConfigRepository.findAllByUserUuid(userUuid);
        Result result = new Result(ResultCode.SUCCESS, btopMenuConfigList);
        return result;
    }

    //首页左侧菜单
    @PostMapping("/leftMenu")
    public Result leftMenu(@RequestParam("url") String url) {
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userUuid = buserConfig.getBuser().getUserUuid();
        List<BleftMenuConfig> bleftMenuConfigList = bleftMenuConfigRepository.findAllByUserUuid(userUuid);
        Result result = new Result(ResultCode.SUCCESS, bleftMenuConfigList);
        return result;
    }

    //首页中部数据
    @PostMapping("/centerData")
    public Result centerData(@RequestParam("url") String url, @RequestParam("page") int page,
                             @RequestParam("size") int size, @RequestParam(value = "top", required = false) String top,
                             @RequestParam(value = "left", required = false) String left) {

        //根据网站地址获取对应用户名
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userName = buserConfig.getBuser().getUserName();
        //根据用户名,头部菜单名,左侧菜单名读取相应文件
        if (StringUtils.isNotBlank(top) && StringUtils.isBlank(left)) {
            return new Result(ResultCode.SUCCESS, FileUtil.getJsonByTop(url, userName, top, page, size));
        } else if (StringUtils.isNotBlank(left) && StringUtils.isBlank(top)) {
            return new Result(ResultCode.SUCCESS, FileUtil.getJsonByLeft(url, userName, left, page, size));
        } else if (StringUtils.isNotBlank(top) && StringUtils.isNotBlank(left)) {
            return new Result(ResultCode.SUCCESS, FileUtil.getJsonByTopAndLeft(url, userName, top, left, page, size));
        } else {
            return new Result(ResultCode.SUCCESS, FileUtil.getAllJson(url, userName, page, size));
        }
    }


    //首页底部友链
    @PostMapping("/friendLink")
    public Result friendLink(@RequestParam("url") String url) {
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userUuid = buserConfig.getBuser().getUserUuid();
        List<BfriendLink> bfriendLinkList = bfriendLinkRepository.findAllByUserUuid(userUuid);
        Result result = new Result(ResultCode.SUCCESS, bfriendLinkList);
        return result;
    }


    //首页滚动栏
    @PostMapping("/scrollbar")
    public Result scrollbar(@RequestParam("url") String url) {
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userUuid = buserConfig.getBuser().getUserUuid();
        List<BphotoConfig> bphotoConfigList = bphotoConfigRepository.findAllByUserUuid(userUuid);
        Result result = new Result(ResultCode.SUCCESS, bphotoConfigList);
        return result;
    }

    //商城配置信息
    @PostMapping("/config")
    public Result config(@RequestParam("url") String url) {
        BuserConfig buserConfig = buserConfigService.getBuserConfigByUserUrl(url);
        String userUuid = buserConfig.getBuser().getUserUuid();
        BshopConfig bshopConfig = bshopConfigRepository.getBshopConfigByUuid(userUuid);
        Result result = new Result(ResultCode.SUCCESS, bshopConfig);
        return result;
    }

    //商品详情
    @PostMapping("/goodsDetails")
    public Result goodsDetails(@RequestParam("fileUrl") String fileUrl) {
        JSONObject json = FileUtil.getJsonByFile(fileUrl);
        Result result = new Result(ResultCode.SUCCESS, json);
        return result;
    }

    //商品搜索
    @PostMapping("/goodsSearch")
    public Result goodsSearch(@RequestParam("key") String key, @RequestParam("page") int page,
                              @RequestParam("size") int size) {
        RequestObject requestObject = new RequestObject();
        requestObject.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
        requestObject.setQ(key);
        requestObject.setPage_no(new Long(page));
        requestObject.setPage_size(new Long(size));
        JSONObject json = tbkService.getTbkItem(requestObject);
        Result result = new Result(ResultCode.SUCCESS, json);
        return result;
    }

}




