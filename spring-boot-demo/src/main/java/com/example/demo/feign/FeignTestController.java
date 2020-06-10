package com.example.demo.feign;

import com.example.demo.xmlconvert.UserForJacksonXML;
import org.springframework.web.bind.annotation.*;
//测试Java8时间日期(如LocalDate)序列化问题
//解决的方案是引入jason-datatype-jsr.jar，spring-boot已经默认引入
@RestController
public class FeignTestController {
        // 单独测试SpringMVC框架时，如果返回对象但不加上ResponseBody标签会报错，但使用Spring-Boot搭建框架后，即使不加标签依旧可以运行
        //不过，为了保证程序健壮性，应该加上
        @RequestMapping(value = "localtime",method = {RequestMethod.POST})
        public UserForJacksonXML containLocalTime(@RequestBody UserForJacksonXML user){
            System.out.println("Server");
            System.out.println(user.name+"="+user.birthday);
            return user;
        }
}
