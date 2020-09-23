package com.employeesystem.employeesystem.repository.model.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScheduleRepository extends JpaRepository<Schedule, String > , JpaSpecificationExecutor<Schedule> {
}
