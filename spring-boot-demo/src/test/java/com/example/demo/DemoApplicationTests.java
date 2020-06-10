package com.example.demo;

import com.example.demo.common.controller.HelloController;
import com.example.demo.concurrent.Schedule;
import com.example.demo.feign.RemoteService;
import com.example.demo.rabbitmq.Sender;
import com.example.demo.xmlconvert.UserForJacksonXML;
import feign.Feign;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.concurrent.Future;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)//SpringBootTest和SpringBootApplication取代了SpringApplicationConfiguration注解，装配DemoApplication应用中的对象
@WebAppConfiguration//使用web包必须添加的注解
public class DemoApplicationTests {
    private MockMvc mvc;
//    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }
    /**
     * andExpect()
     * @throws Exception
     */
//    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello")));
    }

    @Value("${my.spring-boot.application-propertitie}")
    private String value;
//    @Test
    public void  testProperties() throws Exception{
        Assert.assertEquals(value,"Test成功");
    }

    @Autowired
    private Schedule schedule;
//    @Test
    public void testAsyn() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> future1 = schedule.doTaskOne();
        Future<String> future2 = schedule.doTaskTwo();
        Future<String> future3 = schedule.doTaskThree();
        while(true){
            if(future2.isDone()&&future1.isDone()&&future3.isDone())
                break;
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Resource(name = "JsonMessageConverter")
    private ObjectFactory converter;
    public RemoteService initializeServiceFegin(String url) {
        //使用Feign独立测试消费时，存在发送对象的情况，因此需要使用编码器和反编码器进行json序列化处理
        //为了保持序列映射和Spring-Cloud服务端一致，此处使用SpringEncoder和SpringDecoder来定制包含jackson-datatype-jsr310映射的编译器和反编译器
        RemoteService service = Feign.builder()
                .encoder(new SpringEncoder((ObjectFactory<HttpMessageConverters>)converter))
                .decoder(new SpringDecoder((ObjectFactory<HttpMessageConverters>)converter))
                .contract(new SpringMvcContract())
                .target(RemoteService.class, url);
        return service;
    }


    @Test
    public void test() {
        RemoteService service = this.initializeServiceFegin("http://localhost:8080");
        UserForJacksonXML result=service.remoteCall(new UserForJacksonXML("me", LocalDate.now()));
        System.out.println(result);
    }

    @Autowired
    private  Sender sender;
    @Test
    public void rabbitTest(){
        sender.send();
    }
}
