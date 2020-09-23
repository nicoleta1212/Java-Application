package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.producer.JmsProducer;
import com.employeesystem.employeesystem.repository.model.Schedule.ScheduleRepository;
import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.repository.model.department.DepartmentRepository;
import com.employeesystem.employeesystem.repository.model.employee.Employee;
import com.employeesystem.employeesystem.repository.model.employee.EmployeeRepository;
import com.employeesystem.employeesystem.service.api.EmployeeService;
import com.employeesystem.employeesystem.service.dto.EmployeeDTO;
import com.employeesystem.employeesystem.web.exceptions.EmptyListException;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
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
    @Autowired
    private ScheduleRepository scheduleRepository;

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
        final Department byId = departmentRepository.findById(departId).orElseThrow(() -> new EntityNotFoundException("Department "));
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
        final List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()){
            throw new EmptyListException("The list of employees is empty.");
        }
        return  employeeList;
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id : " + id, id));

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

      final List<Employee> allByLastName = employeeRepository.findAllByLastNameContaining(validWord(lastName));
        if (allByLastName.isEmpty()) {
            throw new EmptyListException("No employees found with this name!");
        } else {
            return allByLastName;
        }
    }

    @Override
    public List<Employee> searchByCity(String city) {
        final List<Employee> allByAddressCity = employeeRepository.findAllByAddressCityContaining(validWord(city));
        if (allByAddressCity.isEmpty()) {
            throw new EmptyListException("There are no employees from " + city + ".");
        } else {
                return allByAddressCity;
        }
    }

    @Override
    public List<Employee> findAllByLastNameAndAddressCity(String lastName, String city) {
        final List<Employee> allByLastNameAndAddressCity = employeeRepository.findAllByLastNameContainingAndAddressCityContaining(validWord(lastName), validWord(city));
        if (allByLastNameAndAddressCity.isEmpty()) {
            throw new EmptyListException("There are no employyes with this name from " + city + ".");
        } else {
            return allByLastNameAndAddressCity;
        }
    }

    @Override
    public List<Employee> findAllByScheduleId(String id) {
        return employeeRepository.findAllByScheduleId(id);
    }

    @Override
    public List<String> sortedList() {

        final List<Employee> all = employeeRepository.findAll();
        final List<String> sortedList = all.stream()
                .map(e -> e.getLastName() + " " + e.getFirstName()).sorted().collect(Collectors.toList());
        if (sortedList.isEmpty()){
            throw new EmptyListException("There are no employees yet to be sorted");
        }
        return sortedList;
    }

    @Override
    public List<Employee> listByGenderAndCity(String gender, String city) {
        final List<Employee> allByGenderAndCity = employeeRepository.findAllByGenderContainingAndAddressCityContaining(validWord(gender), validWord(city));

        if (allByGenderAndCity.isEmpty()) {
            throw new EmptyListException("There are no "+ gender+ " employees from " + city + ".");
        } else {
            return allByGenderAndCity;
        }
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
                .map(e -> e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public List<String> employeesByCity(String city) {
        final List<Employee> all = employeeRepository.findAllByAddressCityContaining(validWord(city));
        final List<String> allByCity = all.stream()
                .map(e -> e.getLastName() + " " + e.getLastName())
                .sorted()
                .collect(Collectors.toList());
        if (allByCity.isEmpty()) {
            throw new EmptyListException("There are no employees from " + city + ".");
        } else {
            return allByCity;
        }
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
    public List<String> search(String name, String city, String start, String end) throws ParseException {

        final List<Employee> employeeList = employeeRepository.searchByDepartmentNameContainingAndAddressCityContaining(validWord(name), validWord(city));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        final List<String> collect = employeeList.stream().filter(e -> e.getDateOfBirth().after(startDate) && e.getDateOfBirth().before(endDate))
                .map(e -> e.getLastName() + " " + e.getFirstName() + ": hired on " + e.getDateOfHiring() + " with id: " + e.getId())
                .sorted()
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new EmptyListException("There are no employees from " + city + " born between " + startDate +" and " + endDate);
        } else {
            return collect;
        }
    }


    private static String validWord(String input) {
        final String[] listOfWords = input.split(" ");
        String regexp = "^[a-zA-Z_ -]+$";
        for (String word : listOfWords) {
            if (!word.matches(regexp)) {
                throw new EmptyListException("Search words must contain only letters.");
            }
        }
        return input;
    }
}
