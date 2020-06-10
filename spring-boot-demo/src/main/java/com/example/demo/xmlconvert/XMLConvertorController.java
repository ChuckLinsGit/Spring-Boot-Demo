package com.example.demo.xmlconvert;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//需要导入jackson-dataformat-xml
//指定consumes和produces返回类型
//需要转换的类需要加上@JacksonXML*+标签
@RestController
public class XMLConvertorController {
    @ResponseBody
    @PostMapping(value = "xmlconvert",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public UserForJacksonXML getUser(@RequestBody UserForJacksonXML user){
        System.out.print(user.name+":"+user.birthday);
        return user;
    }
}
