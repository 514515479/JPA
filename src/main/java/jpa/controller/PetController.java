package jpa.controller;

import jpa.dao.PetDao;
import jpa.domain.Pet;
import jpa.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Author: tobi
 * @Date: 2020/10/8 17:30
 **/
@RestController
public class PetController {

    @Autowired
    private PetDao petDao;

    @GetMapping("/list")
    public Result list() {
        List<Pet> list = petDao.findAll();
        //根据pname排序
        //List<Pet> list = petDao.findAll(Sort.by("pname"));
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page() {
        //0代表第一页
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "pname");
        Page<Pet> page = petDao.findAll(pageable);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        //Optional为空抛异常
        Optional<Pet> op = petDao.findById(id);
        Pet pet = op.get();
        return Result.success(pet);
    }

    @PostMapping("/pet")
    public Result add(@RequestBody Pet pet) {
        //如果pet没有指定id，执行的是新增操作；如果指定了id，会先去数据库查询，存在数据的话，执行更新操作，否则执行新增操作
        return Result.success(petDao.save(pet));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        //先根据id查询，没有数据可以删除就抛异常
        petDao.deleteById(id);
        return Result.success();
    }

    @GetMapping("/findBy")
    public Result findby(@RequestParam("pname")String pname,
                         @RequestParam("color")String color) {
        return Result.success(petDao.findByPnameAndColor(pname, color));
    }

    @GetMapping("/findByIdBetween")
    public Result findByIdBetween() {
        return Result.success(petDao.findByIdBetweenOrderById(1, 2));
    }
}
