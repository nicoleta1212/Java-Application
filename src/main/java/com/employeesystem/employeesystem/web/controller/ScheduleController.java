package com.employeesystem.employeesystem.web;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import com.employeesystem.employeesystem.service.api.ScheduleService;
import com.employeesystem.employeesystem.service.dto.ScheduleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    private EmployeeService employeeService;

    public ScheduleController(ScheduleService scheduleService, EmployeeService employeeService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
    }

    @PutMapping ("/{id}")
    public void update(@NotEmpty @PathVariable String id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(id, scheduleDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule create(@Valid @RequestBody ScheduleDTO scheduleDTO){
        return scheduleService.create(scheduleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getById(@NotEmpty @PathVariable String id){
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@NotEmpty @PathVariable String id){
        scheduleService.delete(id);
    }

    @PutMapping ("/add/{employeeId}")
    public Schedule addSchedule(@NotEmpty @PathVariable String employeeId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.addSchedule(employeeId, scheduleDTO);
    }

    @GetMapping("/schedule")
    public List<Schedule> scheduleNull(){
        return   scheduleService.schedule();
    }
}
