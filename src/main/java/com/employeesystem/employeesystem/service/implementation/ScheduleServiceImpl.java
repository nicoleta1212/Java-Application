package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.repository.model.Schedule.ScheduleRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.repository.model.employee.EmployeeRepository;
import com.employeesystem.employeesystem.service.api.ScheduleService;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule create(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setMonday(scheduleDTO.getMonday());
        schedule.setTuesday(scheduleDTO.getTuesday());
        schedule.setWednesday(scheduleDTO.getWednesday());
        schedule.setThursday(scheduleDTO.getThursday());
        schedule.setFriday(scheduleDTO.getFriday());
        schedule.setSaturday(scheduleDTO.getSaturday());
        schedule.setSunday(scheduleDTO.getSunday());
        return scheduleRepository.save(schedule);

    }

    @Override
    public Schedule getById(String id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule ",id));
    }
    @Override
    public void delete(String id) {
        Schedule byId = scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule ",id));
        scheduleRepository.delete(byId);
    }

    @Override
    public void update(String id, ScheduleDTO scheduleDTO) {
        final Schedule updated = getById(id);
        updated.setMonday(scheduleDTO.getMonday());
        updated.setTuesday(scheduleDTO.getTuesday());
        updated.setWednesday(scheduleDTO.getWednesday());
        updated.setThursday(scheduleDTO.getThursday());
        updated.setFriday(scheduleDTO.getFriday());
        updated.setSaturday(scheduleDTO.getSaturday());
        updated.setSunday(scheduleDTO.getSunday());
        scheduleRepository.save(updated);
    }



    @Override
    public Schedule addSchedule(String employeeId, ScheduleDTO scheduleDTO) {
        final Optional<Employee> empId = employeeRepository.findById(employeeId);
        if (!empId.isPresent()) {
            return null;
        }
        final Employee employee = empId.get();

        // Schedule schedule = new Schedule();
        final Optional<Schedule> scheduleById = scheduleRepository.findById(scheduleDTO.getId());
        if (!scheduleById.isPresent()) {
            return null;
        }
        final Schedule schedule = scheduleById.get();

        schedule.setMonday(scheduleDTO.getMonday());
        schedule.setTuesday(scheduleDTO.getTuesday());
        schedule.setWednesday(scheduleDTO.getWednesday());
        schedule.setThursday(scheduleDTO.getThursday());
        schedule.setFriday(scheduleDTO.getFriday());
        schedule.setSaturday(scheduleDTO.getSaturday());
        schedule.setSunday(scheduleDTO.getSunday());

        employee.setSchedule(schedule);

        final Employee savedEmployee = employeeRepository.save(employee);
        schedule.setEmployee(savedEmployee);

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> schedule() {
        final List<Schedule> all = scheduleRepository.findAll();
       return all.stream()
                .filter(s -> s.getMonday().equalsIgnoreCase("10-16"))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        scheduleRepository.deleteAll();
    }
}

