package com.example.demo.mybatis.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Student {
	private Integer id;
	private String name;
	private Integer age;
	private Integer score;
	private Integer classid;
}
