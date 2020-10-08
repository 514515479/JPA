package jpa.controller;

import jpa.dao.ClazzDao;
import jpa.dao.StudentDao;
import jpa.domain.Clazz;
import jpa.domain.Student;
import jpa.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: tobi
 * @Date: 2020/10/8 21:50
 **/
@RestController
public class One2ManyController {

    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private StudentDao studentDao;

    @PostMapping("/clazz")
    public Result clazz() {
        //添加班级
        Clazz clazz = new Clazz();
        clazz.setCname("S班");
        return Result.success(clazzDao.save(clazz));
    }

    @PostMapping("/student")
    public Result student() {
        //添加学生
        Student student = new Student();
        student.setSname("tobi");

        //如果外键列没有设置，也可以保存成功，外键列允许为空
        Clazz clazz = new Clazz();
        clazz.setCid(1); //setCid的值必须来源于t_clazz表存在的数据，不然会报错
        student.setClz(clazz);
        return Result.success(studentDao.save(student));
    }

    @PutMapping("/clazz")
    public Result updateClazz() {
        //更新班级
        Clazz clazz = new Clazz();
        clazz.setCid(1);
        clazz.setCname("S班");
        //先去数据库查询，存在数据的话，执行更新操作，否则执行新增操作
        return Result.success(clazzDao.save(clazz));
    }

    @PutMapping("/student")
    public Result updateStudent() {
        //添加学生
        Student student = new Student();
        student.setSid(1);
        student.setSname("tobi");

        Clazz clazz = new Clazz();
        clazz.setCid(1);
        student.setClz(clazz);
        //先去数据库查询，存在数据的话，执行更新操作，否则执行新增操作
        return Result.success(studentDao.save(student));
    }

    @GetMapping("/clazz/list")
    public Result list() {
        //select clazz0_.cid as cid1_0_, clazz0_.cname as cname2_0_ from t_clazz clazz0_
        //懒加载：这里的sql语句并没有去查询班级的学生
        List<Clazz> list = clazzDao.findAll();
        List<Student> student = list.get(0).getList();
        student.forEach(x -> {
            System.out.println(x.getSname());
        });
        return Result.success(list);
    }
}
