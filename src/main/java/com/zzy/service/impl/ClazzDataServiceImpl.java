package com.zzy.service.impl;


import com.zzy.mapper.ClazzDataMapper;
import com.zzy.mapper.EmpJobDataMapper;
import com.zzy.pojo.EmpJobData;
import com.zzy.service.ClazzDataService;
import com.zzy.service.EmpJobDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzDataServiceImpl implements ClazzDataService {

    @Autowired
    private ClazzDataMapper clazzDataMapper;

    @Override
    public List<Map<String, Object>> clazzCount() {
        return clazzDataMapper.clazzCount();
    }

    @Override
    public List<Map<String, Object>> degreeCount() {
        return clazzDataMapper.degreeCount();
    }
}