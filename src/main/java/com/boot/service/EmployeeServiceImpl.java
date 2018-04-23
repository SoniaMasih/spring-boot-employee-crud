package com.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.entity.Employee;
import com.boot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Iterable<Employee> listAllEmployee() {
		logger.debug("listAllEmployee called");
        return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		logger.debug("getEmployeeById called");
		return employeeRepo.findOne(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		logger.debug("saveEmployee called");
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		logger.debug("deleteEmployee called");
		employeeRepo.delete(id);
	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		logger.debug("deleteEmployee called");
		employeeRepo.delete(employee);
	}

	@Override
	public Iterable<Employee> saveEmployees(Iterable<Employee> employees) {
		logger.debug("saveEmployees called");
		return employeeRepo.save(employees);
	}

	@Override
	public void deleteEmployees(Iterable<Employee> employees) {
		logger.debug("deleteEmployees called");
		for(Employee employee : employees){
			if(employee.getId()!=null){
				employee = getEmployeeById(employee.getId());
				if(employee!=null)
					employeeRepo.delete(employee);
			}
		}
	}
	
	
	
	
	
	

}
