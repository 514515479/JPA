package jpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: tobi
 * @Date: 2020/10/8 17:09
 **/

@Data
@Entity(name = "t_pet")  //实体类和数据库表的关系映射
public class Pet {

    @Id //id唯一值
    @GeneratedValue(strategy = GenerationType.IDENTITY) //指定主键的生成策略，这里设置自增
    private int id;

    @Column //表示pname是个普通的列，Cloumn有很多属性：唯一 非空 长度 等等，可以点击去看看
    private String pname;

    @Column
    private String color;
}
