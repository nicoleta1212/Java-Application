package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.employee.Employee;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class DepartmentDTO implements Serializable {
    private String id;
    @NotEmpty(message = "Name must not be empty.")
    private String name ;

    private List<Employee> employee;

    public DepartmentDTO(String name, List<Employee> employee) {
        this.name = name;
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
