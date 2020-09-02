package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.repository.model.carteIdentitate.CarteIdentitate;
import com.employeesystem.employeesystem.repository.model.department.Department;

import java.io.Serializable;
import java.util.Date;

public class EmployeeDTO implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date dateOfHiring;
    private boolean working ;
    private Address address ;
    private CarteIdentitate carteIdentitate;
    private Department department;
    private Schedule schedule;



    public EmployeeDTO() {
    }

    public EmployeeDTO( String firstName, String lastName, Date dateOfBirth, String gender,
                        Date dateOfHiring, boolean working, Address address, CarteIdentitate carteIdentitate,
                        Department department, Schedule schedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateOfHiring = dateOfHiring;
        this.working = working;
        this.address = address;
        this.carteIdentitate = carteIdentitate;
        this.department = department;
        this.schedule = schedule;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(Date dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public CarteIdentitate getCarteIdentitate() {
        return carteIdentitate;
    }

    public void setCarteIdentitate(CarteIdentitate carteIdentitate) {
        this.carteIdentitate = carteIdentitate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
