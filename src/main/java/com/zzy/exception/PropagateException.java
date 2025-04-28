package com.zzy.exception;

public class PropagateException extends RuntimeException {
    // 默认消息构造函数
    public PropagateException() {
        super("对不起，该班级下有学生，不能直接删除。");
    }

    // 自定义消息构造函数
    public PropagateException(String message) {
        super(message);
    }
}