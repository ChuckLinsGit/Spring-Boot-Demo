package com.example.demo.exceptionhandler;

public class MyException extends  Exception{
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }
}
