package com.boot.test;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import com.boot.controller.EmployeeController;
import com.boot.entity.Employee;
import com.boot.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@MockBean
	private EmployeeController employeeController;

	//@Test
	public void testListEmployee() throws Exception {
		Employee employee = new Employee(1, "abc");
		List<Employee> allEmployees = singletonList(employee);
		given(employeeController.list(Mockito.any(Model.class))).willReturn(allEmployees);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/list");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"name\":\"abc\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testShowEmployee() throws Exception{
		Employee employee = new Employee(1, "abc");
		//List<Employee> allEmployees = singletonList(employee);
		given(employeeController.showEmployee(Mockito.any(Integer.class),Mockito.any(Model.class))).willReturn(employee);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/searchEmployee/1");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"name\":\"abc\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testSaveEmployee() throws Exception{
		String inputJSON = "[{\"id\":1,\"name\":\"abc\"}]";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/employee/addUpdateEmployees")
	                        .accept(MediaType.APPLICATION_JSON)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(inputJSON))
	                .andExpect(status().isOk());
	}
	
	@Test
	public void testSaveEmployees() throws Exception{
		String inputJSON = "[{\"id\":1,\"name\":\"abc\"},{\"id\":2,\"name\":\"xyz\"}]";
		this.mockMvc.perform(post("/employee/addUpdateEmployees")
		        .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
		        .andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception{
		Employee employee = new Employee(1, "abc");
		List<Employee> allEmployees = singletonList(employee);
		given(employeeController.list(Mockito.any(Model.class))).willReturn(allEmployees);
		String inputJSON = "{\"id\":1,\"name\":\"xyz\"}";
		this.mockMvc.perform(put("/employee/update/1")
		        .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
		        .andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception{
		Employee employee = new Employee(1, "abc");
		List<Employee> allEmployees = singletonList(employee);
		given(employeeController.list(Mockito.any(Model.class))).willReturn(allEmployees);
		this.mockMvc.perform(delete("/employee/delete/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteEmployees() throws Exception{
		String inputJSON1 = "[{\"id\":1,\"name\":\"abc\"},{\"id\":2,\"name\":\"xyz\"}]";
		this.mockMvc.perform(post("/employee/addUpdateEmployees")
		        .contentType(MediaType.APPLICATION_JSON).content(inputJSON1));
		String inputJSON2 = "[{\"id\":1},{\"id\":2}]";
		this.mockMvc.perform(delete("/employee/deleteEmployees")
		        .contentType(MediaType.APPLICATION_JSON).content(inputJSON2))
		        .andExpect(status().isOk());
	}

}
