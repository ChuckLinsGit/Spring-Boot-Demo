package com.example.demo.jdbc;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JDBC多数据源测试类
 * 注意：在运行前应当将JPA或者其他数据源代码注释掉，否则会产生冲突
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MutilDatasourceTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Test
    public void test() throws Exception {

        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("INSERT INTO student (name,age,score,classid) VALUES(?,?,?,?)", "Chuck",1, 100, 20);
        jdbcTemplate1.update("INSERT INTO student (name,age,score,classid) VALUES(?,?,?,?)", "Chuck",2, 100, 20);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("INSERT INTO student (name,age,score,classid) VALUES(?,?,?,?)", "Chuck",1, 100, 30);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from student", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from student", String.class));

    }


}
