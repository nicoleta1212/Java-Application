package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.repository.model.Schedule.ScheduleRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.repository.model.employee.EmployeeRepository;
import com.employeesystem.employeesystem.service.api.ScheduleService;
import com.employeesystem.employeesystem.service.dto.ScheduleDTO;
import com.employeesystem.employeesystem.web.exceptions.EmptyListException;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Schedule> findAll() {
        final List<Schedule> scheduleList = scheduleRepository.findAll();
        if (scheduleList.isEmpty()){
            throw new EmptyListException("The list of schedule is empty.");
        }
        return scheduleList;
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
        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule: "+ id));
    }
    @Override
    public void delete(String id) {
        Schedule byId = getById(id);
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
            throw new EntityNotFoundException("Employee with id: " + employeeId);
        }
        final Employee employee = empId.get();

        final Optional<Schedule> scheduleById = scheduleRepository.findById(scheduleDTO.getId());
        if (!scheduleById.isPresent()) {
            throw new EntityNotFoundException("Schedule with id: " + scheduleDTO.getId());
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


    @Override
    public void deleteAll() {
        scheduleRepository.deleteAll();
    }

    @Override
    public List<Schedule> scheduleBySpec(Specification<Schedule> specs) {
        final List<Schedule> resultList = scheduleRepository.findAll(Specification.where(specs));
        if (resultList.isEmpty()){
            throw new EmptyListException("There are no employees that match this specific criteria.");
        }
        return resultList;
    }
}

