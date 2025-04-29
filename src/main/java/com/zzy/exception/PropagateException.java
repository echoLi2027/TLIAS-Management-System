package com.zzy.exception;

import com.zzy.pojo.Emp;
import com.zzy.pojo.Student;

public class PropagateException extends RuntimeException {
    // 默认消息构造函数
    public PropagateException(Student student) {
        super("对不起，该班级下有学生，不能直接删除。");
    }

    public PropagateException(Emp emp){
        super("对不起，当前部门下有员工，不能直接删除。");
    }

    // 自定义消息构造函数
    public PropagateException(String message) {
        super(message);
    }
}