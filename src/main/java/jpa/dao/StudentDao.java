package jpa.dao;

import jpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: tobi
 * @Date: 2020/10/8 22:03
 **/
public interface StudentDao extends JpaRepository<Student, Integer> {
}
