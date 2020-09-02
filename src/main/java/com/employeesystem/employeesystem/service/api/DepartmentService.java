package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    Department create(DepartmentDTO departmentDTO);
     void delete(String id);
     List<Department> getAll();
     void update(String id, DepartmentDTO departmentDTO);
     Department getById(String id);
    List<Department> search(String name);
    List<Employee> searchDeptAndEmployee  (String name, String firstName);

}
