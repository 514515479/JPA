package jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 学生类
 *
 * @Author: tobi
 * @Date: 2020/10/8 21:12
 **/
@Entity(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    @Column
    private String sname;

    @ManyToOne //多对一：多个学生属于一个班级
    @JoinColumn(name = "cid") //指定外键列，如果外键列没有指定name属性，默认名称clz
    @JsonIgnore
    private Clazz clz;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Clazz getClz() {
        return clz;
    }

    public void setClz(Clazz clz) {
        this.clz = clz;
    }

    //toString要去掉打印依赖对方的字段
    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' + '}';
    }
}
