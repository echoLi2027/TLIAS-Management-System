package com.zzy.controller;

import com.zzy.pojo.EmpJobData;
import com.zzy.pojo.Result;
import com.zzy.service.EmpJobDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private EmpJobDataService empJobDataService;

    @GetMapping("/empJobData")
    public Result jobData(){

        EmpJobData data = empJobDataService.search();

        return Result.success(data);

    }

    @GetMapping("/empGenderData")
    public Result genderData(){

        List<Map<String,Object>> data = empJobDataService.genderData();

        return Result.success(data);

    }
}
