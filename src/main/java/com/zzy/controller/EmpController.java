package com.zzy.controller;


import com.zzy.pojo.*;
import com.zzy.service.DeptService;
import com.zzy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService service;

    /*@GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize){
        log.info("page number and page size are:{},{}", page,pageSize);
        PageResult pageResult = service.queryPage(page, pageSize);
        return Result.success(pageResult);
    }*/


    @GetMapping
    public Result list(QueryParam params,
        @RequestParam(defaultValue = "1")Integer page,
        @RequestParam(defaultValue = "10")Integer pageSize
    ){
        log.info("query params: {},{},{}", params, page,pageSize);
        PageResult pageResult = service.queryPage(params, page, pageSize);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("save info: {}",emp);
        service.insert(emp);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@RequestParam List<Integer> ids){

        log.info("delete by ids:{}",ids);

        service.deleteByIds(ids);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result searchById(@PathVariable Integer id){

        log.info("search by id:{}", id);

        Emp emp = service.searchById(id);

        return Result.success(emp);

    }

    @PutMapping
    public Result updateById(@RequestBody Emp emp){

        log.info("emp is: {}",emp);

        service.update(emp);

        return Result.success();
    }


    @GetMapping("/list")
    public Result getAll(){
        List<Emp> empList = service.getAll();

        return Result.success(empList);
    }




}
