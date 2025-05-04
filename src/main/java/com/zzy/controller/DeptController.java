package com.zzy.controller;


import com.zzy.anno.LogOperation;
import com.zzy.pojo.Dept;
import com.zzy.pojo.Result;
import com.zzy.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService service;


    @GetMapping
    public Result list(){
        List<Dept> deptList = service.findAll();
        return Result.success(deptList);
    }

    @LogOperation
    @DeleteMapping
    //   public Result delDept(Integer id)// also works, it required that request param name is same as this name("id") in the method defined
    public Result delDept(@RequestParam("id") Integer id){
//        System.out.println("id is : "+ id);
        log.info("according to id delete dept, id is : {}",id);
        service.delById(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping
//    here request param is a json variable, using @RequestBody can convert it directly into an obj
    public Result insertDept(@RequestBody Dept dept){
//        System.out.println("name is : "+ dept);
        log.info("insert a new dept, dept name is : {}",dept.getName());
        service.insert(dept);
        return Result.success();
    }

    @LogOperation
    @RequestMapping("/{id}")
    public Result searchById(@PathVariable Integer id){
//        System.out.println("path id is : " + id);
        log.debug("search dept by id, id is {}",id);
        Dept dept = service.searchById(id);
        return Result.success(dept);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println(dept);
        log.info("update info about the dept:{}",dept);
        service.update(dept);
        return Result.success();
    }

}
