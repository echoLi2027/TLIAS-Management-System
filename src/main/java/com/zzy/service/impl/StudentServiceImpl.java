package com.zzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzy.mapper.EmpLogMapper;
import com.zzy.mapper.StudentMapper;
import com.zzy.pojo.EmpLog;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;
import com.zzy.service.EmpLogService;
import com.zzy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult pageQuery(StudentParam param) {

        PageHelper.startPage(param.getPage(), param.getPageSize());

        List<Student> students = studentMapper.pageQuery(param);

        Page<Student> p = (Page<Student>) students;

        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void addStu(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.addStu(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void updateStu(Student student) {
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateStu(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void violationProcess(Integer id, Short score) {

        studentMapper.violationProcess(id,score,LocalDateTime.now());
    }
}
