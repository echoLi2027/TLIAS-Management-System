package com.zzy.service;

import com.zzy.pojo.EmpLog;
import com.zzy.pojo.PageResult;
import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;

import java.util.List;

public interface StudentService {


    PageResult pageQuery(StudentParam param);

    void addStu(Student student);

    Student findById(Integer id);

    void updateStu(Student student);

    void deleteByIds(List<Integer> ids);

    void violationProcess(Integer id, Short score);
}
