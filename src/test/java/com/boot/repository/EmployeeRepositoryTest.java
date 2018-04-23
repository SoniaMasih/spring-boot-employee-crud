package com.boot.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.entity.Employee;
import com.boot.repositoryconfig.RepositoryConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();
		employee.setName("Test Employee");
		// save employee, verify has ID value after saving
		assertNull(employee.getId()); // null before save
		employeeRepo.save(employee);
		assertNotNull(employee.getId()); // not null after save
		// Get from DB
		Employee getEmployee = employeeRepo.findOne(employee.getId());
		// should not be null
		assertNotNull(getEmployee);
		// should equal
		assertEquals(employee.getId(), getEmployee.getId());
		assertEquals(employee.getName(), getEmployee.getName());
		// update name and save
		getEmployee.setName("New Name");
		employeeRepo.save(getEmployee);
		// get from DB, should be updated
		Employee getUpdatedEmployee = employeeRepo.findOne(getEmployee.getId());
		assertEquals(getEmployee.getName(), getUpdatedEmployee.getName());
		
		//verify count of employees in DB
        long employeeCount = employeeRepo.count();
        //get all employees, list should all the record
        Iterable<Employee> employees = employeeRepo.findAll();
        int count = 0;
        for(Employee emp : employees){
            count++;
        }
        assertEquals(count, employeeCount);
	}
}
