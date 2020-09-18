package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.api.DepartmentService;
import com.employeesystem.employeesystem.service.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll(){
       return departmentService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Valid @RequestBody DepartmentDTO departmentDTO){
       return departmentService.create(departmentDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@NotEmpty @PathVariable String id){
        departmentService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Department with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@NotEmpty @PathVariable String id){
      return  ResponseEntity.ok(departmentService.getById(id)) ;
    }

    @PutMapping("/{id}")
    public Map<String, Boolean> update(@NotEmpty @PathVariable String id, @Valid @RequestBody DepartmentDTO departmentDTO){
        departmentService.update(id, departmentDTO);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Department with id " + id + " was updated", Boolean.TRUE);
        return response;
    }

    @GetMapping("/searchDepartment")
    public ResponseEntity< List<Department>> searchDepartment(@NotEmpty @RequestParam String  name){
        return ResponseEntity.ok(departmentService.search(name));
    }

    @GetMapping("/searchDepartmentAndEmployee")
    public ResponseEntity< List<Employee>> searchDepartmentAndEmployee(@NotEmpty @RequestParam String name, @NotEmpty @RequestParam String firstName){
        return ResponseEntity.ok(departmentService.searchDeptAndEmployee(name, firstName));
    }

}
