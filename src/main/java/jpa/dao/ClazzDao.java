package jpa.dao;

import jpa.domain.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: tobi
 * @Date: 2020/10/8 21:49
 **/
public interface ClazzDao extends JpaRepository<Clazz, Integer> {
}
