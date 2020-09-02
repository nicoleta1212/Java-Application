package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.producer.JmsProducer;
import com.employeesystem.employeesystem.repository.model.Schedule.ScheduleRepository;
import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.department.DepartmentRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.repository.model.employee.EmployeeRepository;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    JmsProducer producer;
    @Autowired
    private JmsTemplate jmsTemplate;

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    private ScheduleRepository scheduleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ScheduleRepository scheduleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Employee createEmployee(String departId, EmployeeDTO employeeDTO) {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(employeeDTO.getGender());
        employee.setDateOfHiring(employeeDTO.getDateOfHiring());
        employee.setWorking(employeeDTO.isWorking());

        employee.setAddress((employeeDTO.getAddress()));
        employee.setCarteIdentitate(employeeDTO.getCarteIdentitate());
        employee.setSchedule(employeeDTO.getSchedule());


        Department department = new Department();
        final Optional<Department> byId = departmentRepository.findById(departId);
        if (!byId.isPresent()) {
            return null;
        }
        final Department department1 = byId.get();
        employee.setDepartment(department1);
        final Employee savedEmployee = employeeRepository.save(employee);
        employeeList.add(savedEmployee);
        department.setEmployee(employeeList);
        producer.send(savedEmployee);
        System.out.println("Message has been sent successfully!");
        return employeeRepository.save(savedEmployee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee idToDelete = employeeRepository.getOne(id);
        employeeRepository.delete(idToDelete);
        jmsTemplate.convertAndSend("employee", "Employee   " + idToDelete.getLastName() + " " + idToDelete.getFirstName() + " has been deleted.");
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException(id));

    }

    @Override
    public void update(String id, EmployeeDTO employeeDTO) {
        Employee updated = getById(id);

        updated.setFirstName(employeeDTO.getFirstName());
        updated.setLastName(employeeDTO.getLastName());
        updated.setDateOfBirth(employeeDTO.getDateOfBirth());
        updated.setGender(employeeDTO.getGender());
        updated.setDateOfHiring(employeeDTO.getDateOfHiring());
        updated.setWorking(employeeDTO.isWorking());

        updated.setAddress(employeeDTO.getAddress());
        updated.setCarteIdentitate(employeeDTO.getCarteIdentitate());
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
        return employeeRepository.findAllByLastName(lastName);

    }

    @Override
    public List<Employee> searchByCity(String city) {
        return employeeRepository.findAllByAddressCity(city);
    }

    @Override
    public List<Employee> findAllByLastNameAndAddressCity(String lastName, String city) {
        return employeeRepository.findAllByLastNameAndAddressCity(lastName, city);
    }

    @Override
    public List<Employee> findAllByScheduleId(String id) {
        return employeeRepository.findAllByScheduleId(id);
    }

    @Override //  working
    public List<String> sortedList() {

        final List<Employee> all = employeeRepository.findAll();
        return all.stream()
                .map(e -> e.getLastName() + " " + e.getFirstName()).sorted().collect(Collectors.toList());
    }

    @Override //  working
    public List<Employee> listByGender(String gender, String city) {
        final List<Employee> allByGender = employeeRepository.findAllByGender(gender);
       return allByGender.stream().filter(e -> e.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

       /* return allEmployees.stream()
               // .map(e->e.getLastName() + " " + e.getLastName()) //lista cu numele List<String> / map changes the type of data
                .collect(Collectors.toList());*/
    }

    @Override //lista departamentelor
    public List<String> department() {
        final List<Employee> all = employeeRepository.findAll();
        return all.stream().map(e -> e.getDepartment().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override //lista angajatilor care lucreaza la cleaning - working
    public List<String> employeesPerDepartment() {
        final List<Department> all = departmentRepository.findAll();
        return all.stream()
                .filter(e -> e.getName().equalsIgnoreCase("cleaning"))
                .flatMap((dep -> dep.getEmployee().stream()))
                .map(e->e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());

    }

    @Override // working
    public List<String> employeesByCity(String city) {
        final List<Employee> all = employeeRepository.findAllByAddressCity(city);
        return all.stream()
                .map(e->e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());
    }

    @Override // working
    public List<String> employeesCleaning() {
        final List<Employee> all = employeeRepository.findAll();
        return all.stream()
                .filter(e -> e.getDepartment().getName().equalsIgnoreCase("cleaning"))
                .map(Employee::getLastName).collect(Collectors.toList());
    }

    @Override //  nr of all employees of all departments - working
    public long allEmployees() {
        final List<Department> all = departmentRepository.findAll();
        return all.stream().flatMap(dep -> dep.getEmployee().stream()).count(); // mapToLong(dep -> dep.getEmployee().size()).sum();
       /* final long cleaning = all.stream().filter(e -> e.getDepartment().getName().equalsIgnoreCase("cleaning"))
                .count();
        final long marketing = all.stream().filter(e -> e.getDepartment().getName().equalsIgnoreCase("marketing"))
                .count();
        return cleaning + marketing;*/
    }

    @Override // working
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
    public List<String> customEmployee() throws ParseException {
        final List<Employee> all = employeeRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2010-01-01");
        Date end = sdf.parse("2016-01-01");
        return all.stream().filter(e -> e.getDepartment().getName().equalsIgnoreCase("cleaning"))
                // .filter(e -> e.getAddress().getCity().equalsIgnoreCase("brasov"))
                //.filter(e -> e.getDateOfBirth().after(start) && e.getDateOfBirth().before(end))
                .map(e -> e.getLastName() + " " + e.getFirstName() + ": hired on " + e.getDateOfHiring())
                .sorted()
                .collect(Collectors.toList());


    }

    // working
    public List<Employee> schedule(String monday, String tuesday,String wednesday,String thursday,String friday,String saturday,String sunday) {
        final List<Employee> schedule = employeeRepository.findAllByScheduleMondayOrScheduleTuesdayOrScheduleWednesdayOrScheduleThursdayOrScheduleFridayOrScheduleSaturdayOrScheduleSunday(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
          return   schedule.stream().filter(e->e.getDepartment().getName().equalsIgnoreCase("cleaning")).collect(Collectors.toList());
    }

    @Override // working
    public List<String> search(String name, String city, String start, String end) throws ParseException {
        final List<Employee> employeeList = employeeRepository.searchByDepartmentNameAndAddressCity(name, city);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        return employeeList.stream().filter(e -> e.getDateOfBirth().after(startDate) && e.getDateOfBirth().before(endDate))
                .map(e -> e.getLastName() + " " + e.getFirstName() + ": hired on " + e.getDateOfHiring() + " with id: " + e.getId())
                .sorted()
                .collect(Collectors.toList());
    }
}
