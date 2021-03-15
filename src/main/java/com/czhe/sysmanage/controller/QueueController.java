package com.czhe.sysmanage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author czhe
 * @version 1.0
 * @create 2021/3/15 15:53
 * @description
 **/
@Api(tags = "排队")
@RequestMapping("/queue")
@RestController
public class QueueController {

    private static final String TEAM = "team";

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation(value = "加入队列", notes = "传入用户id加入队列")
    @PostMapping("/add")
    public void addQueue(@RequestParam("userId") String userId) {
        redisTemplate.opsForList().rightPush(TEAM, userId);
    }

    @ApiOperation(value = "获取当前队列人数", notes = "获取当前队列人数")
    @GetMapping("/count")
    public String count() {
        Long size = redisTemplate.opsForList().size(TEAM);
        return "队列人数: " + size;
    }


    @ApiOperation(value = "获取当前队列详情", notes = "获取当前队列详情")
    @GetMapping("/detail")
    public String detail() {
        List listData = redisTemplate.opsForList().range(TEAM, 0, -1);
        return listData.toString();
    }

    @ApiOperation(value = "离开当前队列", notes = "传入用户id从队列删除")
    @GetMapping("/remove")
    public void remove(@RequestParam("userId") String userId) {
        redisTemplate.opsForList().remove(TEAM, 0, userId);
    }


    @ApiOperation(value = "排队结束", notes = "先进先出第一位离开队列")
    @GetMapping("/leave")
    public void leave() {
        redisTemplate.opsForList().leftPop(TEAM);
    }

    @ApiOperation(value = "获取当前位置", notes = "传入用户id获取位置")
    @GetMapping("/getPosition")
    public String getPosition(@RequestParam("userId") String userId) {
        List listData = redisTemplate.opsForList().range(TEAM, 0, -1);
        int position = listData.indexOf(userId);
        return String.format("当前排名:%s, 前面还有%s人, 后面还有%s人", position + 1, position, listData.size() - position - 1);
    }

    @ApiOperation(value = "插队", notes = "传入插队类型和要插队的用户ID以及被插队的用户ID")
    @GetMapping("/saveUser")
    public void saveUser(@RequestParam("action") String action, @RequestParam("userId") String userId, @RequestParam("userToId") String userToId) {
        if ("after".equals(action)) {
            redisTemplate.opsForList().rightPush(TEAM, userToId, userId);
        } else if ("before".equals(action)) {
            redisTemplate.opsForList().leftPush(TEAM, userToId, userId);
        }
        //如果插入到指定位置先获取指定位置的用户
        /*List listData = redisTemplate.opsForList().range(TEAM, 0, -1);
        redisTemplate.opsForList().leftPush(TEAM, listData.get(i), userId);*/
    }


}