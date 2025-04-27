package com.zzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzy.mapper.EmpExprMapper;
import com.zzy.mapper.EmpLogMapper;
import com.zzy.mapper.EmpMapper;
import com.zzy.pojo.*;
import com.zzy.service.EmpLogService;
import com.zzy.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService logService;



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


//    (rollbackFor = Exception.class) no matter which kind of exception will roll back the transaction
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Emp emp) throws Exception {

        try {
//        1. implement emp info
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

//        2. save info into DB
            empMapper.insert(emp);

//        int i = 1/0;

//        will throw the exception, but still commit the transaction

        if (true){
            throw new Exception("there is exception~");
        }

//        not safe, what if emp.getExprList() is null
      /*
        Integer empId = emp.getId();
        emp.getExprList().forEach(empExpr -> empExpr.setEmpId(empId));
        empExprMapper.insertExpr(emp.getExprList());
*/
//        3. store a batch of expr
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertExpr(exprList);
            }
        } finally {
//            record operation log
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            logService.insertLog(empLog);
        }


    }


}
