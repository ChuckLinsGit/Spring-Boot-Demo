package com.example.demo.jdbc;

import com.example.demo.common.entity.Student;
import com.example.demo.JDBC.JDBCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//springboot进行测试时必须加上Runwith和SpringBootTest标签用以启动Spring容器，才能保证能使用Spring容器的依赖注入
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JDBCServiceTest {

    @Autowired
    private JDBCService jdbcService;

    @Test
    public void test(){
        Student student=new Student();
        student.setAge(20);
        student.setName("Chuck");
        student.setScore(100);
        student.setClassid(1);
        jdbcService.create(student);
        jdbcService.getAllStudent();
        //jdbcService.deleteByName("Chuck");
    }
}
