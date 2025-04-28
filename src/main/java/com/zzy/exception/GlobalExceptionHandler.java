package com.zzy.exception;

import com.zzy.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result ex(Exception e){
        e.printStackTrace();// print exception in the stack
//        catch the exception, response a error
        return Result.error("operation failed, please contact admin.");
    }

    @ExceptionHandler
    public Result duplicateKeyException(DuplicateKeyException e){
        String message = e.getMessage();
        int index = message.indexOf("Duplicate entry");
        String[] strings = message.substring(index).split(" ");
        return Result.error(strings[2]+" already existed.");
    }

    @ExceptionHandler
    public Result propEx(PropagateException e){
        return Result.error(e.getMessage());
    }



}
