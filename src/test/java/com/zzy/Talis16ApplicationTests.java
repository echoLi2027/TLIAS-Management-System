package com.zzy;

import com.zzy.exception.GlobalExceptionHandler;
import com.zzy.exception.PropagateException;
import com.zzy.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Talis16ApplicationTests {

    @Test
    void contextLoads() {
        if (true){
            throw new PropagateException("test");
        }
    }

    @Autowired
    GlobalExceptionHandler handler;

    @Test
    void exceptionTest() {
        Result result = handler.propEx(new PropagateException());
        System.out.println("Error message: " + result.getMsg());
        // 或者使用断言
        // assertEquals("对不起，该班级下有学生，不能直接删除。", result.getMsg());
    }

}
