package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(String departId,EmployeeDTO employeeDTO);

    void deleteEmployee( String id);

    List<Employee> getAll();

    Employee getById(String id);

    void update(String id, EmployeeDTO employeeDTO);

    List<Employee> search(String name);

    List<Employee> searchByCity(String city);

    List<Employee> findAllByLastNameAndAddressCity(String lastName, String city);

    List<Employee> findAllByScheduleId(String id);

    List<String> sortedList();

    List<String> department();
    List<String> employeesPerDepartment();

    List<Employee> listByGender(String gender, String city );

    List<String > employeesByCity(String city);

    List<String> employeesCleaning();

    long allEmployees();
    List<String> departmentMostEmployees();
    List<String> customEmployee() throws ParseException;
    List<Employee> schedule(String monday, String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday);

    List<String> search(String departName, String city, String start, String end) throws ParseException;
}
