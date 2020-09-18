package com.employeesystem.employeesystem.web;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
   public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @PostMapping("/{departId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@NotEmpty @PathVariable String departId,@Valid @RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(departId,employeeDTO);
    }

    @PutMapping(path = "/{id}",consumes = "application/json", produces = "application/json")
    public void update(@NotEmpty @PathVariable String id,@Valid @RequestBody EmployeeDTO employeeDTO){
         employeeService.update(id, employeeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@NotEmpty @PathVariable String  id){
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@NotNull @PathVariable String id){
        employeeService.deleteEmployee(id);

    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Employee>> searchByName(@NotEmpty @RequestParam String lastName){
       return ResponseEntity.of(Optional.of(employeeService.search(lastName))) ;
    }

    @GetMapping("/searchByCity")
    public ResponseEntity<List<Employee>> searchByCity( @NotEmpty @RequestParam String city){
        return ResponseEntity.of(Optional.of(employeeService.searchByCity(city)));
    }

    @GetMapping("/searchByNameAndCity")
    public ResponseEntity<List<Employee>> searchByLastNameAndCity( @NotEmpty @RequestParam String lastName,@NotEmpty @RequestParam String city){
        return ResponseEntity.of(Optional.of(employeeService.findAllByLastNameAndAddressCity(lastName, city)));
    }

    @GetMapping("/searchByScheduleId")
    public ResponseEntity<List<Employee>> searchByScheduleId( @NotEmpty @RequestParam String id){
        return ResponseEntity.of(Optional.of(employeeService.findAllByScheduleId(id)));
    }

    @GetMapping("/sorted")
    public List<String> sorted(){
        return  employeeService.sortedList();
    }

    @GetMapping("/gender")
    public List<Employee> listByGender(@NotEmpty @RequestParam String gender  ,@NotEmpty @RequestParam String city ){
        return  employeeService.listByGender(gender, city);
    }

    @GetMapping("/department")
    public List<String> department(){
        return employeeService.department();
    }

    @GetMapping("/employeePerDepartment")
    public List<String> employeePerDepartment(){
        return employeeService.employeesPerDepartment();
    }

    @GetMapping("/employeesbyCity")
    public List<String> employeesByCity(@NotEmpty @RequestParam String city){
        return employeeService.employeesByCity(city);
    }

    @GetMapping("/employeesCleaning")
    public List<String> employeesCleaning(){
        return employeeService.employeesCleaning();
    }

    @GetMapping("/allEmployees")
    public long allEmployees(){
        return employeeService.allEmployees();
    }

    @GetMapping("/departmentMostEmployees")
    public List<String> departmentMostEmployees(){
       return   employeeService.departmentMostEmployees();
    }

    @GetMapping("/customEmployee")
    public List<String> customEmployee() throws ParseException {
        return   employeeService.customEmployee();
    }

    @GetMapping("/schedule")
    public List<Employee> schedule ( @RequestParam String monday,
                                     @RequestParam String tuesday,
                                     @RequestParam String wednesday,
                                     @RequestParam String thursday,
                                     @RequestParam String friday,
                                     @RequestParam String saturday,
                                     @RequestParam String sunday){
        return   employeeService.schedule(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
    }

    @GetMapping("/search")
    public List<String> search(@NotEmpty @RequestParam String name,@NotEmpty @RequestParam String city,
                               @NotEmpty @RequestParam String start,@NotEmpty @RequestParam String end) throws ParseException {
        return   employeeService.search(name,city,start,end);
    }

}
