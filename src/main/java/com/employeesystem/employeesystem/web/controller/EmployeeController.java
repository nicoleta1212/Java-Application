package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;
import com.employeesystem.employeesystem.web.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/{departId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@NotEmpty @PathVariable String departId, @Valid @RequestBody EmployeeDTO employeeDTO) throws ParseException, InvalidFormatException {
        return employeeService.createEmployee(departId, employeeDTO) ;
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> update(@NotEmpty @PathVariable String id, @Valid @RequestBody EmployeeDTO employeeDTO) throws ParseException, InvalidFormatException {
        employeeService.update(id, employeeDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee with id: " + id + " has been updated:", Boolean.TRUE);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@NotEmpty @PathVariable String id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@NotNull @PathVariable String id) {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }



    @GetMapping("/searchByName")
    public ResponseEntity<List<Employee>> searchByName(@NotEmpty @RequestParam String lastName) {

        return ResponseEntity.of(Optional.of(employeeService.search(lastName)));
    }

    @GetMapping("/searchByCity")
    public ResponseEntity<List<Employee>> searchByCity(@NotEmpty @RequestParam String city) {
        return ResponseEntity.of(Optional.of(employeeService.searchByCity(city)));
    }

    @GetMapping("/searchByNameAndCity")
    public ResponseEntity<List<Employee>> searchByLastNameAndCity(@NotEmpty @RequestParam String lastName, @NotEmpty @RequestParam String city) {
        return ResponseEntity.of(Optional.of(employeeService.findAllByLastNameAndAddressCity(lastName, city)));
    }

    @GetMapping("/searchByScheduleId")
    public ResponseEntity<List<Employee>> searchByScheduleId(@NotEmpty @RequestParam String id) {
        return ResponseEntity.of(Optional.of(employeeService.findAllByScheduleId(id)));
    }

    @GetMapping("/sorted")
    public List<String> sorted() {
        return employeeService.sortedList();
    }

    @GetMapping("/listByGenderAndCity")
    public List<Employee> listByGenderAndCity(@NotEmpty @RequestParam String gender, @NotEmpty @RequestParam String city) {
        return employeeService.listByGenderAndCity(gender, city);
    }

    @GetMapping("/allDepartments")
    public List<String> allDepartments() {
        return employeeService.allDepartments();
    }

    @GetMapping("/employeesFromCleaningDepartment")
    public List<String> employeesFromCleaningDepartment() {
        return employeeService.employeesFromCleaningDepartment();
    }

    @GetMapping("/employeesbyCity")
    public List<String> employeesByCity(@NotEmpty @RequestParam String city) {
        return employeeService.employeesByCity(city);
    }

    @GetMapping("/nrOfAllEmployeesFromAllDepartments")
    public long nrOfAllEmployeesFromAllDepartments() {
        return employeeService.nrOfAllEmployeesFromAllDepartments();
    }

    @GetMapping("/departmentMostEmployees")
    public List<String> departmentMostEmployees() {
        return employeeService.departmentMostEmployees();
    }


    @GetMapping("/search")
    public List<String> search(@NotEmpty @RequestParam String name, @NotEmpty @RequestParam String city,
                               @NotEmpty @RequestParam String start, @NotEmpty @RequestParam String end) throws ParseException{
        return employeeService.search(name, city, start, end);
    }


}
