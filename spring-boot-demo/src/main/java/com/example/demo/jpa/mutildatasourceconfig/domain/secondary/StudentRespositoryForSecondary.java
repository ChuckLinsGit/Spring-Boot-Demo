package com.example.demo.jpa.mutildatasourceconfig.domain.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRespositoryForSecondary extends JpaRepository<StudentForSecondary, Long> {
    //jpa通过解析方法名自动创建SQL语句
    //也可通过@Query、JPQL语法或SPEL表达式创建SQL语句
    //特别的是插入数据是使用CrudRepository.save(…)方法实现
    StudentForSecondary findBySid(Integer Sid);

    List<StudentForSecondary> findByNameAndAge(String name, Integer age);

    @Query("from StudentForSecondary s where s.name= :name")
    List<StudentForSecondary> findWithName(@Param("name") String name);

    //注意：使用Update/Delete Query必须加上Modifying和Transactional标签
    @Transactional
    @Modifying
    @Query("update StudentForSecondary s set s.age =:age where s.sid=:sid")
    void updateWithId(@Param("sid") Integer sid, @Param("age") Integer age);

    void deleteBySid(Integer sid);

    @Transactional
    @Modifying
    @Query("delete from StudentForSecondary s where s.name= :name")
    void deleteWithName(@Param("name") String name);
}
