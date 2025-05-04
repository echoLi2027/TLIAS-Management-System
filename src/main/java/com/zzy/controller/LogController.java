package com.zzy.controller;

import com.zzy.mapper.OperateLogMapper;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.Result;
import com.zzy.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/page")
    Result findByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){

        log.info("query params: {},{}", page,pageSize);

        PageResult pageResult = logService.findByPage(page, pageSize);

        log.info("query result: {}", pageResult);

        return Result.success(pageResult);
    }
}
