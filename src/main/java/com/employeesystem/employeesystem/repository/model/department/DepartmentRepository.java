package com.employeesystem.employeesystem.repository.model.department;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findAllByName(String name);
    @Query("select e from Employee e where e.firstName = firstName ")
    List<Employee> findAllByNameAndByEmployeeFirstName(String name, String firstName);
}
