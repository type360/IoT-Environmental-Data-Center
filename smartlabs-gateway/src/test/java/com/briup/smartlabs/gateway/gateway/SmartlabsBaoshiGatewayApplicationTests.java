package com.briup.smartlabs.gateway.gateway;

import com.briup.smartlabs.gateway.bean.Student;
import com.briup.smartlabs.gateway.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SmartlabsBaoshiGatewayApplicationTests {
	@Autowired
	StudentDao stuDao;
	@Test
	void contextLoads() {
		Student stu = new Student();
		stu.setName("lisi");
		stu.setAge(18);
		stu.setGender("M");
		
		//stuDao.save(stu);
		
		Optional<Student> s = stuDao.findById(2L);
		if(s.isPresent()) {
			System.out.println(s.get());
		}
		List<Student> list = stuDao.findByName("zhangsan");
		System.out.println(list);
		list = stuDao.findByNameLike("z");
		System.out.println(list);
	}
	
	@Test
	public void test() {
		System.out.println(Integer.parseInt("8000", 16));
	}

}
