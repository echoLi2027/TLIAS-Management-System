package com.zzy.service.impl;

import com.zzy.mapper.DeptMapper;
import com.zzy.pojo.Dept;
import com.zzy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper mapper;

    @Override
    public List<Dept> findAll() {
        return mapper.findAll();
    }

    @Override
    public void delById(Integer id) {
        mapper.delete(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        mapper.insert(dept);
    }

    @Override
    public Dept searchById(Integer id) {
        return mapper.searchById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        mapper.update(dept);
    }
}
