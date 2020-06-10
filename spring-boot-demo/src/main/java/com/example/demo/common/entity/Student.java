package com.example.demo.common.entity;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class Student {
    private Integer sid;
    private String  name;
    private Integer age;
    private Integer score;
    private Integer classid;
}
