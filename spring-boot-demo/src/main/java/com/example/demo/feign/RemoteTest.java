package com.example.demo.feign;


import com.example.demo.xmlconvert.UserForJacksonXML;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;

/**
 * 测试时应该去掉Security依赖
 */
public class RemoteTest{
    public  RemoteService initializeServiceFegin(String url) {
        //为了解决LocalDate等Java8后增加的时间类序列化时出现将年月日等处理成数组类型的情况
        //SpringCloud加入了jackson-datatype-jsr310序列映射类
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(mapper);
        ObjectFactory<HttpMessageConverters> converter = ()-> new HttpMessageConverters(jsonConverter);

        //使用Feign独立测试消费时，存在发送对象的情况，因此需要使用编码器和反编码器进行json序列化处理
        //为了保持序列映射和Spring-Cloud服务端一致，此处使用SpringEncoder和SpringDecoder来定制包含jackson-datatype-jsr310映射的编译器和反编译器
        RemoteService service = Feign.builder()
                .encoder(new SpringEncoder(converter))
                .decoder(new SpringDecoder(converter))
                .contract(new SpringMvcContract())
                .target(RemoteService.class, url);
        return service;
    }


    public static void main(String[] args) {
        RemoteService service = new RemoteTest().initializeServiceFegin("http://localhost:8080");
        UserForJacksonXML result=service.remoteCall(new UserForJacksonXML("me", LocalDate.now()));
        System.out.println(result);
    }
}
