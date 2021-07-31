package com.example.demo;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeCRUDTest {
	
	@Autowired
	private EmployeeRepository repo;

	//Test Case 1 - Insert into DB 
	@Test
	@Rollback(false)
	public void testCreateEmployee() {
		Employee savedEmployee = repo.save(new Employee("Nivi","Senthil","abc@gmail.com", "1"));
		System.out.println("Id is -- "+savedEmployee.getId());
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	//Test Case 2 - Insert into DB - Unique Employee Name Else throw error
	@Test
	@Rollback(false)
	public void testCreateEmployee1() {
		Employee savedEmployee = repo.save(new Employee("Ni","Senthil","abc@gmail.com", "1"));
		System.out.println("Id is -- "+savedEmployee.getId());
		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	//Test Case 3 - Search for data -
	@Test
	public void testFindEmployeeByName() {
	    Employee employee = repo.getById((long) 2);    
	    assertThat(employee.getFirstName()).isEqualTo("Nivetha");
	}
	
	@Test
	public void testListEmployees() {
	    List<Employee> employees = (List<Employee>) repo.findAll();
	    assertThat(employees).size().isGreaterThan(0);
	}
	
	//Update Employee
	@Test
	@Rollback(false)
	public void testUpdateEmployee() {
	    Employee employee = repo.getById((long) 2);  
	    employee.setRanking("2");
	    repo.save(employee);
	    Employee employee1 = repo.getById((long) 2);    
	    assertThat(employee1.getFirstName()).isEqualTo("Nivetha");
	}
	
	//Delete employee
	@Test
	@Rollback(false)
	public void testDeleteEmployee() {
	    Employee employee = repo.getById((long) 5);
	     
	    repo.deleteById(employee.getId());
	     
	    Optional<Employee> deletedEmployee = repo.findById((long) 5);
	     
	    assertThat(deletedEmployee).isNull();       
	     
	}
}
