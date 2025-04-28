package com.zzy.controller;

import com.zzy.pojo.PageResult;
import com.zzy.pojo.Result;
import com.zzy.pojo.Student;
import com.zzy.pojo.StudentParam;
import com.zzy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result pageQuery(StudentParam param){
        log.info("student query param info : {}", param);
        PageResult result = studentService.pageQuery(param);
        return Result.success(result);
    }

    @PostMapping
    public Result addStu(@RequestBody Student student){
        log.info("student info is : {}",student);
        studentService.addStu(student);
        return Result.success();

    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("path varaible is : {}", id);

        Student student = studentService.findById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result updateStu(@RequestBody Student student){
        log.info("student info is : {}", student);
        studentService.updateStu(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable String ids){

        log.info("delete by id:{}",ids);

        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        studentService.deleteByIds(list);

        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violationProcess(@PathVariable Integer id, @PathVariable Short score){
        log.info(" student id is {}, score is {}", id, score);

        studentService.violationProcess(id,score);

        return Result.success();
    }

}
