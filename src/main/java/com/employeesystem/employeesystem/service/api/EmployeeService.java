package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;
import com.employeesystem.employeesystem.web.exceptions.InvalidFormatException;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(String departId, EmployeeDTO employeeDTO) throws ParseException, InvalidFormatException;

    void deleteEmployee( String id);

    List<Employee> getAll();

    Employee getById(String id);

    void update(String id, EmployeeDTO employeeDTO) throws ParseException, InvalidFormatException;

    List<Employee> search(String name);

    List<Employee> searchByCity(String city);

    List<Employee> findAllByLastNameAndAddressCity(String lastName, String city);

    List<Employee> findAllByScheduleId(String id);

    List<String> sortedList();

    List<String> allDepartments();
    List<String> employeesFromCleaningDepartment();

    List<Employee> listByGenderAndCity(String gender, String city );

    List<String > employeesByCity(String city);

    long nrOfAllEmployeesFromAllDepartments();
    List<String> departmentMostEmployees();

    List<String> search(String departName, String city, String start, String end) throws ParseException;

}
