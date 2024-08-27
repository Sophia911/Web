package com.se_backend;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常处理部分
@RestControllerAdvice
public class ErrorCatch {
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in main");
        System.out.println(ex.toString());
        return new Result("main有异常！","00000",null) ;
    }
}
