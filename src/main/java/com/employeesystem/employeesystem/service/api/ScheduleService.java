package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.service.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    Schedule create(ScheduleDTO scheduleDTO);

    void delete(String id);

    Schedule getById(String id);

    void update(String id, ScheduleDTO scheduleDTO);

    List<Schedule> findAll();

    Schedule addSchedule(String employeeId, ScheduleDTO scheduleDTO);

     public void deleteAll();
}
