package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.service.api.ScheduleService;
import com.employeesystem.employeesystem.service.dto.ScheduleDTO;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/getAll")
    public List<Schedule> getAll(){
       return scheduleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule create(@Valid @RequestBody ScheduleDTO scheduleDTO){
        return scheduleService.create(scheduleDTO);
    }

    @PutMapping ("/{id}")
    public void update(@NotEmpty @PathVariable String id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(id, scheduleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getById(@NotEmpty @PathVariable String id){
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@NotEmpty @PathVariable String id){
        scheduleService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Schedule with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }

    @PutMapping ("/add/{employeeId}")
    public Schedule addSchedule(@NotEmpty @PathVariable String employeeId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.addSchedule(employeeId, scheduleDTO);
    }


    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        scheduleService.deleteAll();
    }

    @GetMapping("/searchBySpec")
    public ResponseEntity<List<Schedule>> searchScheduleBySpecifications(@SearchSpec Specification<Schedule> specs){
        return ResponseEntity.of(Optional.of(scheduleService.scheduleBySpec(specs)));
    }
}
