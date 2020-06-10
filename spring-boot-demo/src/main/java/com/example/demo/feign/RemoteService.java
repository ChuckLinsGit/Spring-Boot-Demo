package com.example.demo.feign;

import com.example.demo.xmlconvert.UserForJacksonXML;
import org.springframework.web.bind.annotation.PostMapping;

public interface RemoteService {

//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @PostMapping("localtime")
    public UserForJacksonXML remoteCall(UserForJacksonXML user);
//        @Headers({"Content-Type: application/json","Accept: application/json"})
//        @RequestLine("POST /localtime")
//        public String remoteCall(String name);
}
