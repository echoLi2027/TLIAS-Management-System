package com.zzy.controller;

import com.zzy.pojo.Clazz;
import com.zzy.pojo.ClazzParam;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.Result;
import com.zzy.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result queryClassPage(ClazzParam clazzParam){
        log.info("clazz query info is : {}", clazzParam);

        PageResult resultPage = clazzService.queryClassPage(clazzParam);

        return Result.success(resultPage);

    }

    @PostMapping
    public Result add(@RequestBody Clazz clazz){

        log.info("clazz add info is : {}", clazz);

        clazzService.add(clazz);

        return Result.success();
    }

    @RequestMapping("/{id}")
    public Result searchById(@PathVariable Integer id){
        log.info("clazz search info is : {}", id);

        Clazz clazz = clazzService.searchById(id);

        return Result.success(clazz);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("clazz update info is : {}", clazz);

        clazzService.update(clazz);

        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("clazz delete id is : {}",id);

        clazzService.deleteById(id);

        return Result.success();
    }

    @RequestMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}
