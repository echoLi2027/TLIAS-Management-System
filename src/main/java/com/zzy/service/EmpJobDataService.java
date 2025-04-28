package com.zzy.service;

import com.zzy.pojo.EmpJobData;

import java.util.List;
import java.util.Map;

public interface EmpJobDataService {
    /**
     * get all department name and the count in all departments respectively
     * @return
     */
    EmpJobData search();

    List<Map<String, Object>> genderData();
}
