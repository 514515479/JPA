package jpa.dao;

import jpa.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
