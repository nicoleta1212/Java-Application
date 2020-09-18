package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.department.DepartmentRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.service.api.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(DepartmentDTO departmentDTO) {

        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setEmployee(departmentDTO.getEmployee());

        return departmentRepository.save(department);
    }

    @Override
    public void delete(String id) {
        final Department one = departmentRepository.getOne(id);
        departmentRepository.delete(one);

    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void update(String id, DepartmentDTO departmentDTO) {
        Department updated = departmentRepository.getOne(id);
        updated.setName(departmentDTO.getName());
        updated.setEmployee(departmentDTO.getEmployee());
        departmentRepository.save(updated);

    }

    @Override
    public Department getById(String id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException(id));
    }

    @Override
    public List<Department> search(String name) {
            return departmentRepository.findAllByName(name);
        }

    @Override
    public List<Employee> searchDeptAndEmployee(String name, String firstName) {
        return departmentRepository.findAllByNameAndByEmployeeFirstName(name, firstName);
    }


    }





