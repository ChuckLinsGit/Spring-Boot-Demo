package com.example.demo.jpa.mutildatasourceconfig.domain.primary;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class StudentForPrimary {
    @Id
//    主键产生策略通过GenerationType来指定。GenerationType是一个枚举，它定义了主键产生策略的类型。
//    AUTO自动选择一个最适合底层数据库的主键生成策略。这个是默认选项，即如果只写@GeneratedValue，等价于@GeneratedValue(strategy=GenerationType.AUTO)。
//    auto策略，也就是主键序列化,而mysql是不支持的 oracle支持的 所有在添加的时候才会报错 Table 'mybatis_test.hibernate_sequence' doesn't exist,
//    所以我们要主动设置id的策略 如MySQL会自动对应auto increment。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer classid;
}
