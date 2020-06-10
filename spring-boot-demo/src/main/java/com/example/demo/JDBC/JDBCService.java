package com.example.demo.JDBC;

import com.example.demo.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JDBCService implements IJDBCService{

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Student student) {
        jdbcTemplate.update(
                "INSERT INTO student (sid,name,age,score,classid) VALUES(?,?,?,?,?)",
                 student.getSid(),student.getName(),student.getAge(),student.getScore(),student.getClassid());
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("DELETE FROM student WHERE name =?",name);
    }

    @Override
    public Integer getAllStudent() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM student",Integer.class);
    }
}
