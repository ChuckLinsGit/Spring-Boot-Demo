package com.example.demo.exceptionhandler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HasExcetptionController {
    @RequestMapping("callException")
    public String exceptionCause() throws MyException {
        throw new MyException("Error");
    }
}
