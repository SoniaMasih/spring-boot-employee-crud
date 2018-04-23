package com.boot.service;

import com.boot.entity.Employee;

public interface EmployeeService {
	Iterable<Employee> listAllEmployee();

	Employee getEmployeeById(Integer id);

	Employee saveEmployee(Employee employee);

    void deleteEmployee(Integer id);
    
    public void deleteEmployee(Employee employee);
    
    Iterable<Employee> saveEmployees(Iterable<Employee> employees);
    
    void deleteEmployees(Iterable<Employee> employees);
}
