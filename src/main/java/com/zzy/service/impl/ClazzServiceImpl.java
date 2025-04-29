package com.zzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzy.exception.PropagateException;
import com.zzy.mapper.ClazzMapper;
import com.zzy.mapper.EmpLogMapper;
import com.zzy.mapper.EmpMapper;
import com.zzy.mapper.StudentMapper;
import com.zzy.pojo.*;
import com.zzy.service.ClazzService;
import com.zzy.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult queryClassPage(ClazzParam clazzParam) {

        PageHelper.startPage(clazzParam.getPage(), clazzParam.getPageSize());

        List<Clazz> clazzes = clazzMapper.queryPage(clazzParam);
        if (!CollectionUtils.isEmpty(clazzes)){
            LocalDate today = LocalDate.now();
            clazzes.forEach(clazz -> {
                if (today.isAfter(clazz.getEndDate())){
                    clazz.setStatus("已结课");
                }else if (today.isBefore(clazz.getBeginDate())){
                    clazz.setStatus("未开班");
                }else {
                    clazz.setStatus("在读中");
                }
            });
        }

        Page<Clazz> p = (Page<Clazz>) clazzes;

        return new PageResult(p.getTotal(),p.getResult());

    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.add(clazz);
    }

    @Override
    public Clazz searchById(Integer id) {
        Clazz clazz = clazzMapper.searchById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {

//        1. first check where this clazz related to any student
        List<Student> students = studentMapper.findByClazzId(id);

        if (CollectionUtils.isEmpty(students)){
//            2.1 if there is no student related to this clazz, then we can delete this clazz
            clazzMapper.deleteById(id);
        }else {
//            2.2 otherwise throw an exception;
            throw new PropagateException(students.get(0));
        }
    }

    @Override
    public List<Clazz> findAll() {

        return clazzMapper.findAll();
    }
}
