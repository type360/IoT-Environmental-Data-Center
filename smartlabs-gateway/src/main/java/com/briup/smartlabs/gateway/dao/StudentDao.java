package com.briup.smartlabs.gateway.dao;

import com.briup.smartlabs.gateway.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long>{
	List<Student> findByName(String name);
	List<Student> findByNameLike(String name);
}
