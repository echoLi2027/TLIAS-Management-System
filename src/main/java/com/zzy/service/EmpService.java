package com.zzy.service;

import com.zzy.pojo.Dept;
import com.zzy.pojo.Emp;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.QueryParam;

import java.util.List;


public interface EmpService {

    /**
     * find emp by current page and page size
     * @param page
     * @param pageSize
     * @return
     */
//    PageResult queryPage(Integer page, Integer pageSize);

    /**
     * find emp by search condition and page and page size
     * @param param
     * @return
     */
    PageResult queryPage(QueryParam param, Integer page, Integer pageSize);
}
