package jpa.dao;

import jpa.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: tobi
 * @Date: 2020/10/8 17:27
 **/
public interface PetDao extends JpaRepository<Pet, Integer> {

    /**
     * 自定义查询
     * 注意事项：
     *      返回值根据实际的业务需求定义
     *      方法的名称必须满足规范 findByXXX  以findBy固定开始，XXX属性名称
     *
     */
    List<Pet> findByPname(String pname);

    List<Pet> findByColor(String color);

    List<Pet> findByPnameAndColor(String pname, String color);

    /**
     * 查询id在某个区间范围内的
     * @param minId
     * @param maxId
     * @return
     */
    List<Pet> findByIdBetweenOrderById(Integer minId, Integer maxId);

    /**
     * jpql查询
     * 语法：select attrName1, attrName2... from entityName;
     * 注意事项：
     *      1.不能出现表名、列名，只能出现 java 类名、属性名，区分大小写
     *      2.出现的 sql 关键字是一样的意思，关键字不区分大小写
     *      3.不能写 select *，要写 select 别名：可以写成
     *          1）from jpa.domain.Pet
     *          2）select pet from jpa.domain.Pet pet  (起别名)
     * @return
     */
    @Query(value = "from jpa.domain.Pet")
    List<Pet> loadPetsList();

    /**
     * jpql查询
     * select id, pname, color from jpa.domain.Pet pet
     *
     * 问题：查询的结果集并没有直接封装到Pet中，会报转换失败
     * org.springframework.core.convert.ConverterNotFoundException:
     * No converter found capable of converting from type [java.lang.Integer]
     * to type [@org.springframework.data.jpa.repository.Query jpa.domain.Pet]
     *
     * 解决方法一：用 List<Object[]> 去接收
     * @return
     */
    @Query(value = "select id, pname, color from jpa.domain.Pet pet")
    List<Object[]> loadPetsList2();

    /**
     * jpql查询
     * select id, pname, color from jpa.domain.Pet pet
     *
     * 问题：查询的结果集并没有直接封装到Pet中，会报转换失败
     * org.springframework.core.convert.ConverterNotFoundException:
     * No converter found capable of converting from type [java.lang.Integer]
     * to type [@org.springframework.data.jpa.repository.Query jpa.domain.Pet]
     *
     * 解决方法二：用 new jpa.domain.Pet(id, pname, color) 去接收
     * @return
     */
    @Query(value = "select new jpa.domain.Pet(id, pname, color) from jpa.domain.Pet pet")
    List<Object[]> loadPetsList3();
}
