package com.example.demo.mybatis.DAO;

import com.example.demo.mybatis.bean.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IStudentDAO{
	//使用注释
	@Insert("INSERT INTO STUDENT (NAME,AGE,SCORE,CLASSID) VALUES (#{student.name},#{student.age},#{student.score},#{student.classid})")
	public void saveStudentCache(@Param("student") Student student);

	public List<Student> getStudentByConditionsWithIf(Map map);

	public List<Student> getStudentByConditionsWithWhere(Map map);
	
	public List<Student> getStudentByConditionsWithWhere(Student stu);
	
	public List<Student> getStudentByConditionsWithChoose(Student stu);
	
	public List<Student> getStudentByConditionsWithForeach(List<Student> students);

}
