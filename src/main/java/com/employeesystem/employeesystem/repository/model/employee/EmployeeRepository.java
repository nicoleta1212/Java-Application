package com.employeesystem.employeesystem.repository.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    List<Employee> findAllByLastName(String lastName);

    List<Employee> findAllByAddressCity(String city);

    List<Employee> findAllByLastNameAndAddressCity(String lastname, String city);

    List<Employee> findAllByScheduleId(String id);

    List<Employee> searchByDepartmentNameAndAddressCity(String name, String city);

    List<Employee> findAllByGender(String gender);

    List<Employee> findAllByScheduleMondayOrScheduleTuesdayOrScheduleWednesdayOrScheduleThursdayOrScheduleFridayOrScheduleSaturdayOrScheduleSunday(String monday, String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday);
}
