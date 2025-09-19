package com.zzy.service;

import com.zzy.pojo.PageResult;
import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;

public interface LogService {

    PageResult findByPage(Integer page, Integer pageSize);
}
