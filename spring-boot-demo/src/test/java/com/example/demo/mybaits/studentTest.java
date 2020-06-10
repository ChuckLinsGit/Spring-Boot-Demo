package com.example.demo.mybaits;

import com.example.demo.mybatis.DAO.IStudentDAO;
import com.example.demo.mybatis.bean.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@MapperScan("com.example.demo.mybatis.DAO")
public class studentTest {
//	使用AutoWired标签注入mapper会报：can not autowire，no bean of IStudent type found
//	但不影响运行，因为mapper类需要由mybatis框架生成代理对象后才能被找到
//	但神奇的是，使用Resource标签并不会报错，可能于Resource装配顺序有关
//  @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入
//  @Resource装配顺序
//  1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
//  2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
//  3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
//  4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；

    //无法手动使用被Spring容器管理的SqlSession，否则将报错，官方说明如下：
    //With MyBatis-Spring you don't need to use SqlSessionFactory directly because your beans can be injected with a thread safe SqlSession that automatically commits, rollbacks and closes the session based on Spring's transaction configuration.SqlSessionTemplate
   //因此如果想要使用Sqlsession实现事务管理，只需获得注入的SqlSession并调用其方法如selectOne()等即可实现自动提交、回滚、关闭等操作。
    @Autowired
    private SqlSession sqlSession;

    @Autowired
//    @Resource
    private IStudentDAO stuDAO;

    @Test
    public void noSession() {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", 0);
        map.put("score", 60);
        List<Student> stus = stuDAO.getStudentByConditionsWithWhere(map);
        for (Student stu : stus) {
            System.out.println(stu);
        }
        Student student = new Student();
        student.setAge(20);
        student.setName("Chuuuck");
        student.setScore(100);
        student.setClassid(2);
        stuDAO.saveStudentCache(student);


        //无法手动使用被Spring容器管理的SqlSession，否则将报错
        //java.lang.UnsupportedOperationException: Manual close is not allowed over a Spring managed SqlSession
//        sqlSession.commit();
//        sqlSession.close();
    }
}
