package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.producer.JmsProducer;
import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.department.DepartmentRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.repository.model.employee.EmployeeRepository;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
import com.employeesystem.employeesystem.web.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    JmsProducer producer;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee createEmployee(String departId, EmployeeDTO employeeDTO) throws ParseException {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(employeeDTO.getGender());

        employee.setDateOfHiring(employeeDTO.getDateOfHiring());

        employee.setWorking(employeeDTO.isWorking());

        employee.setAddress((employeeDTO.getAddress()));
        employee.setIdcard(employeeDTO.getIdcard());
        employee.setSchedule(employeeDTO.getSchedule());


        Department department = new Department();
        final Department byId = departmentRepository.findById(departId).orElseThrow(()-> new EntityNotFoundException("Department "));
        employee.setDepartment(byId);
        final Employee savedEmployee = employeeRepository.save(employee);
        employeeList.add(savedEmployee);
        department.setEmployee(employeeList);
        producer.send(savedEmployee);
        System.out.println("Message has been sent successfully!");
        return employeeRepository.save(savedEmployee);
    }



    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id : " + id,id));

    }

    @Override
    public void deleteEmployee(String id) {
        Employee idToDelete = getById(id);
        employeeRepository.delete(idToDelete);
        jmsTemplate.convertAndSend("employee", "Employee   " + idToDelete.getLastName() + " " + idToDelete.getFirstName() + " has been deleted.");
    }
    @Override
    public void update(String id, EmployeeDTO employeeDTO) throws ParseException {
        Employee updated = getById(id);

        updated.setFirstName(employeeDTO.getFirstName());
        updated.setLastName(employeeDTO.getLastName());
        updated.setDateOfBirth(employeeDTO.getDateOfBirth());
        updated.setGender(employeeDTO.getGender());
        updated.setDateOfHiring(employeeDTO.getDateOfHiring());
        updated.setWorking(employeeDTO.isWorking());

        updated.setAddress(employeeDTO.getAddress());
        updated.setIdcard(employeeDTO.getIdcard());
        updated.setSchedule(employeeDTO.getSchedule());

        employeeRepository.save(updated);
        jmsTemplate.convertAndSend("employee", "Employee " + updated.getLastName() + " " + updated.getFirstName() + " has been updated.",
                message -> {
                    message.setStringProperty("JMSXGroupId", updated.getLastName()); //guatanted ordering of messages
                    return message;
                });

    }

    @Override
    public List<Employee> search(String lastName) {
        String regex= "^[a-zA-Z]+$";
        if (!lastName.matches(regex) ){
            throw new InvalidInputException(lastName);
        }
        return employeeRepository.findAllByLastName(lastName);

    }

    @Override
    public List<Employee> searchByCity(String city) {
        String regex= "^[a-zA-Z]+$";
        if (!city.matches(regex) ){
            throw new InvalidInputException(city);
        }
        return employeeRepository.findAllByAddressCity(city);
    }

    @Override
    public List<Employee> findAllByLastNameAndAddressCity(String lastName, String city) {
        String regex= "^[a-zA-Z]+$";
        if (!city.matches(regex) || !lastName.matches(regex)){
            throw new InvalidInputException("value ");
        }
        return employeeRepository.findAllByLastNameAndAddressCity(lastName, city);
    }

    @Override
    public List<Employee> findAllByScheduleId(String id) {
        return employeeRepository.findAllByScheduleId(id);
    }

    @Override
    public List<String> sortedList() {

        final List<Employee> all = employeeRepository.findAll();
        return all.stream()
                .map(e -> e.getLastName() + " " + e.getFirstName()).sorted().collect(Collectors.toList());
    }

    @Override
    public List<Employee> listByGenderAndCity(String gender, String city) {
        final List<Employee> allByGender = employeeRepository.findAllByGender(gender);
        String regex= "^[a-zA-Z]+$";
        if (!city.matches(regex) || !gender.matches(regex)){
            throw new InvalidInputException("value ");
        }
       return allByGender.stream().filter(e -> e.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

    }

    @Override
    public List<String> allDepartments() {
        final List<Employee> all = employeeRepository.findAll();
        return all.stream().map(e -> e.getDepartment().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> employeesFromCleaningDepartment() {
        final List<Department> all = departmentRepository.findAll();
        return all.stream()
                .filter(e -> e.getName().equalsIgnoreCase("cleaning"))
                .flatMap((dep -> dep.getEmployee().stream()))
                .map(e->e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public List<String> employeesByCity(String city) {
        final List<Employee> all = employeeRepository.findAllByAddressCity(city);
        String regex= "^[a-zA-Z]+$";
        if (!city.matches(regex)){
            throw new InvalidInputException(city);
        }
        return all.stream()
                .map(e->e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());
    }


    @Override
    public long nrOfAllEmployeesFromAllDepartments() {
        final List<Department> all = departmentRepository.findAll();
        return all.stream().mapToLong(dep -> dep.getEmployee().size()).sum();

    }

    @Override
    public List<String> departmentMostEmployees() {
        final List<Employee> all = employeeRepository.findAll();
        final long cleaning = all.stream().filter(e -> e.getDepartment().getName().equalsIgnoreCase("cleaning"))
                .count();
        final long marketing = all.stream().filter(e -> e.getDepartment().getName().equalsIgnoreCase("marketing"))
                .count();
        if (cleaning > marketing) {
            return all.stream().map(e -> e.getDepartment().getName())
                    .filter(e -> e.equalsIgnoreCase("cleaning"))
                    .distinct()
                    .collect(Collectors.toList());

        } else {
            return all.stream().map(e -> e.getDepartment().getName())
                    .filter(e -> e.equalsIgnoreCase("marketing"))
                    .distinct()
                    .collect(Collectors.toList());
        }

    }


    @Override
    public List<Employee> schedule(String monday, String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday) {
        final List<Employee> schedule = employeeRepository.findAllByScheduleMondayOrScheduleTuesdayOrScheduleWednesdayOrScheduleThursdayOrScheduleFridayOrScheduleSaturdayOrScheduleSunday(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
          return   schedule.stream().filter(e->e.getDepartment().getName().equalsIgnoreCase("cleaning")).collect(Collectors.toList());
    }

    @Override
    public List<String> search(String name, String city, String start, String end) throws ParseException {
        String regex= "^[a-zA-Z]+$";
        if (!city.matches(regex)){
            throw new InvalidInputException(city);
        }

        final List<Employee> employeeList = employeeRepository.searchByDepartmentNameAndAddressCity(name, city);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        return employeeList.stream().filter(e -> e.getDateOfBirth().after(startDate) && e.getDateOfBirth().before(endDate))
                .map(e -> e.getLastName() + " " + e.getFirstName() + ": hired on " + e.getDateOfHiring() + " with id: " + e.getId())
                .sorted()
                .collect(Collectors.toList());
    }
}
