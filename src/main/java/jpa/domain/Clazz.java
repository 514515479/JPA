package jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 班级类
 *
 * @Author: tobi
 * @Date: 2020/10/8 21:09
 **/
@Entity(name = "t_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    @Column
    private String cname;

    //表示一对多：一个班级有多个学生
    @OneToMany(mappedBy = "clz", fetch = FetchType.EAGER) //mapperBy 创建一对多的映射关系，值是对方外键的属性名称
    @JsonIgnore
    private List<Student> list;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    //toString要去掉打印依赖对方的字段
    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' + '}';
    }
}
