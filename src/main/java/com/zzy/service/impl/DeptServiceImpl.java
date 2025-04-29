package com.zzy.service.impl;

import com.zzy.exception.PropagateException;
import com.zzy.mapper.DeptMapper;
import com.zzy.mapper.EmpMapper;
import com.zzy.pojo.Dept;
import com.zzy.pojo.Emp;
import com.zzy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delById(Integer id) {

//        1.check if there is emp in this dept
        List<Emp> empList = empMapper.findByDeptId(id);
//        2.1if there is emp in this dept, throw a PropagateException, rollback the whole transaction
        if (!CollectionUtils.isEmpty(empList)){
            throw new PropagateException(empList.get(0));
        }
//        2.2if there is no emp in this dept, delete the dept
        deptMapper.delete(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept searchById(Integer id) {
        return deptMapper.searchById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
