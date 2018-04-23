package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entity.Employee;
import com.boot.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
@Api(value="employeerestapp", description="Operations to Employee record")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@ApiOperation(value = "View a list of available employee",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Employee> list(Model model){
        Iterable<Employee> employeeList = employeeService.listAllEmployee();
        return employeeList;
    }
	
	@ApiOperation(value = "Search an employee with an ID",response = Employee.class)
    @RequestMapping(value = "/searchEmployee/{id}", method= RequestMethod.GET, produces = "application/json")
    public Employee showEmployee(@PathVariable Integer id, Model model){
		Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }
	
	@ApiOperation(value = "Add an employee")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveEmployee(@RequestBody Employee employee){
		if(employee!=null){
			employee.setId(null);
			employeeService.saveEmployee(employee);
			return new ResponseEntity("Employee saved successfully", HttpStatus.OK);
		}else{
			return new ResponseEntity("Employee is null.", HttpStatus.OK);
		}
    }
	
	@ApiOperation(value = "Add or Update employees in bulk.This is required list of employees.")
    @RequestMapping(value = "/addUpdateEmployees", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveEmployees(@RequestBody Iterable<Employee> employees){
		employeeService.saveEmployees(employees);
        return new ResponseEntity("Employees saved successfully", HttpStatus.OK);
    }
	
	@ApiOperation(value = "Update an employee")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
		Employee storedEmployee = employeeService.getEmployeeById(id);
		if(storedEmployee!=null){
			storedEmployee.setName(employee.getName());
			employeeService.saveEmployee(storedEmployee);
			return new ResponseEntity("Employee updated successfully", HttpStatus.OK);
		}else{
			return new ResponseEntity("Employee doesnot exist.", HttpStatus.OK); 
		}
		
    }
	
	@ApiOperation(value = "Delete an employee")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
		Employee employee = employeeService.getEmployeeById(id);
		if(employee!=null)
			employeeService.deleteEmployee(employee);
        return new ResponseEntity("Employee deleted successfully", HttpStatus.OK);
    }
	
	@ApiOperation(value = "Delete employees in bulk.")
    @RequestMapping(value = "/deleteEmployees", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteEmployees(@RequestBody Iterable<Employee> employees){
		employeeService.deleteEmployees(employees);
        return new ResponseEntity("Employees deleted successfully", HttpStatus.OK);
    }
}