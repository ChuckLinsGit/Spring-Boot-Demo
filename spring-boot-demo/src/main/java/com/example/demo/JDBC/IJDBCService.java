package com.example.demo.JDBC;

import com.example.demo.common.entity.Student;

public interface IJDBCService {
     /**
     * 新增一个用户
     * @param student
     */
    void create(Student student);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllStudent();
}
