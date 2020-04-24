package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.entity.Person;
import com.czhe.sysmanage.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/8/23 15:38
 * @description
 **/
@Api(tags = "人员接口")
@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation("新增人员")
    @PostMapping("/savePerson")
    public Object savePerson(@Valid @RequestBody Person person) {
        person.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        return personService.insert(person);
    }

    @ApiOperation(value = "查询", notes = "分页查询")
    @GetMapping("/findAllPerson/{page}/{size}")
    public Object findAllPerson(@PathVariable int page, @PathVariable int size) {
        return personService.findAllPerson(page, size);
    }


    @ApiOperation(value = "删除", notes = "根据ID删除")
    @PostMapping("/delPerson/{id}")
    public Object delete(@PathVariable String id) {
        return personService.delete(id);
    }
}
