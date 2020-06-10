package com.example.demo.jpa;

import com.example.demo.jpa.mutildatasourceconfig.domain.primary.StudentForPrimary;
import com.example.demo.jpa.mutildatasourceconfig.domain.primary.StudentRespositoryForPrimary;
import com.example.demo.jpa.mutildatasourceconfig.domain.secondary.StudentForSecondary;
import com.example.demo.jpa.mutildatasourceconfig.domain.secondary.StudentRespositoryForSecondary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MutilDatasourceJPATest {
    @Autowired
    private StudentRespositoryForPrimary respositoryForPrimary;

    @Autowired
    private StudentRespositoryForSecondary respositoryForSecondary;

    @Test
    public void test(){
        StudentForPrimary student=new StudentForPrimary();
        student.setAge(20);
        student.setScore(100);
        student.setClassid(1);
        student.setName("ChuckLin");
        respositoryForPrimary.save(student);

        System.out.println("chuck's number"+ respositoryForPrimary.findWithName("ChuckLin").size());

        StudentForSecondary student2=new StudentForSecondary();
        student2.setAge(20);
        student2.setScore(100);
        student2.setClassid(1);
        student2.setName("ChuckLin");
        respositoryForSecondary.save(student2);

        System.out.println("chuck's number"+ respositoryForSecondary.findWithName("ChuckLin").size());

    }
}
