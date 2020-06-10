package com.example.demo.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRespository extends JpaRepository<StudentForJPA, Long> {
    //在springboot中使用事务管理十分简单，使用Transactional即可启动事务
    //在多数据源的情况下，可以使用value指定对应的事务管理器
    //使用isolation可以指定隔离级别
    //使用propagation可以指定传播方式
    @Transactional(/*value ="",*/isolation = Isolation.DEFAULT,propagation = Propagation.SUPPORTS)
    @Modifying
    @Query("update StudentForJPA s set s.age =:age where s.sid=:sid")
    void updateWithId(@Param("sid") Integer sid, @Param("age") Integer age);
}
