package com.zzy.service.impl;


import com.zzy.mapper.EmpJobDataMapper;
import com.zzy.pojo.EmpJobData;
import com.zzy.service.EmpJobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmpJobDataServiceImpl implements EmpJobDataService {

    @Autowired
    private EmpJobDataMapper empJobDataMapper;

    @Override
    public EmpJobData search() {
        List<Map<String, Object>> jobData = empJobDataMapper.search();

        List<Object> pos = jobData.stream().map(empMapper ->  empMapper.get("pos")).toList();
        List<Object> num =  jobData.stream().map(empMapper -> empMapper.get("num")).toList();

        return new EmpJobData(pos,num);
    }

    @Override
    public List<Map<String, Object>> genderData() {

        return empJobDataMapper.genderData();
    }
}
