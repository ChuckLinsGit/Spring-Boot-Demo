package com.example.demo.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@CacheConfig(cacheNames = "student")//使用spring3自带的缓存，在主类中需要使用EnableCaching来启动缓存
//在Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供者：
//
//        Generic
//        JCache (JSR-107)
//        EhCache 2.x
//        Hazelcast
//        Infinispan
//        Redis
//        Guava
//        Simple
//虽然EhCache已经能够适用很多应用场景，但是由于EhCache是进程内的缓存框架，在集群模式下时，各应用服务器之间的缓存都是独立的，
//因此在不同服务器的进程间会存在缓存不一致的情况。即使EhCache提供了集群环境下的缓存同步策略，但是同步依然需要一定的时间，短暂的缓存不一致依然存在。
//在一些要求高一致性（任何数据变化都能及时的被查询到）的系统和应用中，就不能再使用EhCache来解决了，这个时候使用集中式缓存是个不错的选择，
//所以除了按顺序侦测外，我们也可以通过配置属性spring.cache.type来强制指定。
public interface StudentRespository extends JpaRepository<StudentForJPA, Long> {
    //jpa通过解析方法名自动创建SQL语句
    //也可通过@Query、JPQL语法或SPEL表达式创建SQL语句
    //特别的是插入数据是使用CrudRepository.save(…)方法实现
    @Cacheable//允许该方法使用缓存
    StudentForJPA findBySid(Integer Sid);

    List<StudentForJPA> findByNameAndAge(String name , Integer age);

    @Query("from StudentForJPA s where s.name= :name")
    List<StudentForJPA> findWithName(@Param("name") String name);

    //注意：使用Update/Delete Query必须加上Modifying
    @Modifying
    @Query("update StudentForJPA s set s.age =:age where s.sid=:sid")
    void updateWithId(@Param("sid") Integer sid,@Param("age") Integer age);

    void deleteBySid(Integer sid);

    @Modifying
    @Query("delete from StudentForJPA s where s.name= :name")
    void deleteWithName(@Param("name") String name);
}
