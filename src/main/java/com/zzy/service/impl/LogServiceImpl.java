package com.zzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzy.mapper.OperateLogMapper;
import com.zzy.mapper.StudentMapper;
import com.zzy.pojo.OperateLog;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;
import com.zzy.service.LogService;
import com.zzy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Override
    public PageResult findByPage(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<OperateLog> logs = operateLogMapper.findAll();

        Page<OperateLog> p = (Page<OperateLog>) logs;

        return new PageResult(p.getTotal(), p.getResult());
    }
}
