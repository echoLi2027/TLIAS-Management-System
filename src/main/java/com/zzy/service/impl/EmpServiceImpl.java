package com.zzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzy.mapper.DeptMapper;
import com.zzy.mapper.EmpMapper;
import com.zzy.pojo.Dept;
import com.zzy.pojo.Emp;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.QueryParam;
import com.zzy.service.DeptService;
import com.zzy.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /*@Override
    public PageResult queryPage(Integer page, Integer pageSize) {

//        traditional way for paging
        */
    /*
        Long total = empMapper.total();

        int start = (page - 1) * pageSize;
        List<Emp> emps = empMapper.queryPage(start, pageSize);
*/
    /*

//        page helper api
        PageHelper.startPage(page,pageSize);
//        can only do paging this query, if there is another query needs to paging, it cannot do it.
        List<Emp> empList = empMapper.list();
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult(p.getTotal(), p.getResult());

    }*/


    @Override
    public PageResult queryPage(QueryParam param, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.search(param);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());
    }


}
