package com.zzy.controller;

import com.zzy.pojo.Emp;
import com.zzy.pojo.LoginInfo;
import com.zzy.pojo.Result;
import com.zzy.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("emp info : {}", emp);

        LoginInfo info = empService.findByUsernameAndPassword(emp);

        if (info!=null){
            return Result.success(info);
        }
        return Result.error("username or password wrong.");
    }
}
