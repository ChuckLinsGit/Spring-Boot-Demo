package com.example.demo.REST;

import com.example.demo.common.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "restful")
public class RESTfulController {

    private static Logger logger= LoggerFactory.getLogger(RESTfulController.class);

    @RequestMapping(value = "/user/{sid}",method = RequestMethod.GET)
    public ResponseEntity getStudent(@PathVariable("sid") Integer sid){
        try {
            Student student = new Student();
            student.setAge(20);
            student.setClassid(1);
            student.setSid(sid);
            student.setScore(100);
            student.setName("Chuck");
            logger.info("=====<Get student with id: "+sid+">=====");
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "/user/list/{classid}",method = RequestMethod.GET)
    public ResponseEntity getStudents(@PathVariable("classid") Integer classid){
        try {
            Student student = new Student();
            student.setAge(20);
            student.setClassid(classid);
            student.setSid(1);
            student.setScore(100);
            student.setName("Chuck");
            ArrayList<Student> studentArrayList = new ArrayList<Student>();
            studentArrayList.add(student);
            logger.info("=====<Get student with id: "+classid+">=====");
            return ResponseEntity.ok(studentArrayList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    //跨域设置，注意，必须设置请求方式
    @CrossOrigin(origins = {"http://localhost:63342"},methods = RequestMethod.POST)
    @RequestMapping(value = "/user/{param}",method = RequestMethod.POST)
    public ResponseEntity addStudents(@RequestBody Student student,@PathVariable String param){
        try {
            System.out.println(param);
            System.out.println(student.getName());
            logger.info("=====<Post student "+student.toString()+">=====");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public ResponseEntity changeStudents(@RequestBody Student student){
        try {
            logger.info("=====<Put student "+student.toString()+">=====");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/user/{sid}",method = RequestMethod.DELETE)
    public ResponseEntity dropStudent(@PathVariable("sid") Integer sid){
        try {
            logger.info("=====<Delete student with id: "+sid+">=====");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
