package com.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="employee")
public class Employee {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The employee id.")
    private Integer id;
    
	@Version
    @ApiModelProperty(notes = "The auto-generated version of the employee")
	@JsonIgnore
    private Integer version;
	
	@ApiModelProperty(notes = "The Employee name")
	//@JsonProperty(value="employee_name")
	private String name;
	
	public Employee() {
		
	}
	
	
	
	public Employee(Integer id) {
		super();
		this.id = id;
	}



	public Employee(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	

	public Employee(Integer id, Integer version, String name) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

	@Override
	public String toString() {
		
		return "Employee, "+ id +  name;
		/*return String.format(
				"Employee [id=%s, name=%s]", id, name);*/
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
