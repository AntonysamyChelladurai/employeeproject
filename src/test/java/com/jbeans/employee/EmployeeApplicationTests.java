package com.jbeans.employee;

import com.jbeans.employee.entity.Employee;
import com.jbeans.employee.repository.EmpRepo;
import com.jbeans.employee.service.Empservice;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeApplicationTests {
	@InjectMocks
	Empservice empservice;

	@Mock
	EmpRepo dao;

	@Before("")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

		@Test
		public void getAllEmployeesTest()
		{
			List<Employee> list = new ArrayList<Employee>();

			int year=2023;
			int month=10;
			int date=20;
			Employee empOne = new Employee("E02387", "Emily Davis", "Sr. Manger", "IT", "Research & Development", "Female", "Black", 55, new java.sql.Date(year,month,date), "141,604", 0.15, "0", "Seattle");
			Employee empTwo = new Employee("E04105", "Theodore Dinh", "Technical Architect", "IT", "Manufacturing", "Male", "Asian", 59, new java.sql.Date(year,month,date), "99,975", 0, "0", "Chongqing");
			Employee empThree = new Employee("E77777", "AntonySamy", "Technical Architect", "IT", "Research & Development", "Male", "Asian", 59, new java.sql.Date(year,month,date), "99,975", 0, "0", "Bangalore");

			list.add(empOne);
			list.add(empTwo);
			list.add(empThree);

			when(dao.findAll()).thenReturn(list);

			//test
			List<Employee> empList = empservice.getAllEmpList();

			assertEquals(3, empList.size());
			verify(dao, times(1)).findAll();
		}
}
