package com.demo.web.controller;

import com.demo.dao.entity.Person;
import com.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/23 15:38
 * @description
 **/
@Api(tags = "职员接口")
@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation("新增")
    @PostMapping("/sava")
    public String sava(Person person){
        person.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return  personService.insert(person) > 0 ? "true":"false";
    }

    @ApiOperation("查询")
    @GetMapping("/findAll")
    public Object findAll(){
        return personService.findAll();
    }
}
