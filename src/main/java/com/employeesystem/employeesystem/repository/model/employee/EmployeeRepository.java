package com.employeesystem.employeesystem.repository.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    List<Employee> findAllByLastNameContaining(String lastName);

    List<Employee> findAllByAddressCityContaining(String city);

    List<Employee> findAllByLastNameContainingAndAddressCityContaining(String lastname, String city);

    List<Employee> findAllByScheduleId(String id);

    List<Employee> searchByDepartmentNameContainingAndAddressCityContaining(String name, String city);

    List<Employee> findAllByGenderContainingAndAddressCityContaining(String gender, String city);

}
