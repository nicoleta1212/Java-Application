package com.employeesystem.employeesystem.web;

import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.api.DepartmentService;
import com.employeesystem.employeesystem.service.dto.DepartmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

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
    public void delete(@NotEmpty @PathVariable String id){
        departmentService.delete(id);
    }

    @GetMapping("/{id}")
    public Department getById(@NotEmpty @PathVariable String id){
      return   departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@NotEmpty @PathVariable String id, @Valid @RequestBody DepartmentDTO departmentDTO){
        departmentService.update(id, departmentDTO);
    }

    @GetMapping("/search")
    public List<Department> search(@NotEmpty @RequestParam String  name){
        return departmentService.search(name);
    }

    @GetMapping("/searchByEmployee")
    public List<Employee> searchEmployee(@NotEmpty @RequestParam String name, @NotEmpty @RequestParam String firstName){
        return departmentService.searchDeptAndEmployee(name, firstName);
    }

}
