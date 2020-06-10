package com.example.demo.jpa.mutildatasourceconfig.domain.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRespositoryForPrimary extends JpaRepository<StudentForPrimary, Long> {
    //jpa通过解析方法名自动创建SQL语句
    //也可通过@Query、JPQL语法或SPEL表达式创建SQL语句
    //特别的是插入数据是使用CrudRepository.save(…)方法实现
    StudentForPrimary findBySid(Integer Sid);

    List<StudentForPrimary> findByNameAndAge(String name, Integer age);

    @Query("from StudentForPrimary s where s.name= :name")
    List<StudentForPrimary> findWithName(@Param("name") String name);

    //注意：使用Update/Delete Query必须加上Modifying和Transactional标签
    @Transactional
    @Modifying
    @Query("update StudentForPrimary s set s.age =:age where s.sid=:sid")
    void updateWithId(@Param("sid") Integer sid, @Param("age") Integer age);

    void deleteBySid(Integer sid);

    @Transactional
    @Modifying
    @Query("delete from StudentForPrimary s where s.name= :name")
    void deleteWithName(@Param("name") String name);
}
