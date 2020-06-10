package com.example.demo.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPATest {
    @Autowired
    private StudentRespository respository;

    @Test
    public void test(){
        StudentForJPA student=new StudentForJPA();
        student.setAge(20);
        student.setName("Chuck");
        student.setScore(100);
        student.setClassid(1);
        respository.save(student);
        student.setScore(99);
        respository.save(student);
        student.setName("ChuckLin");
        respository.save(student);

        System.out.println("chuck's number"+respository.findWithName("ChuckLin").size());
        System.out.println("chuck's number"+respository.findByNameAndAge("chuck",20).size());
        System.out.println(respository.findBySid(1).toString());

        respository.updateWithId(1,100);
        respository.deleteBySid(2);
        respository.deleteWithName("ChuckLin");
    }
}
