package com.example.demo.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> myexceptionHandle(HttpServletRequest request,MyException exception){
        ErrorInfo<String> erroe=new ErrorInfo<>();
        erroe.setCode(500);
        erroe.setUrl(request.getRequestURL().toString());
        erroe.setMessage(exception.getMessage());
        erroe.setData("data");
        return erroe;
    }
}
