package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.repository.model.department.Department;
import com.employeesystem.employeesystem.web.exceptions.InvalidDateException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeDTO implements Serializable {
    private String id;
    @NotEmpty(message = "First name cannot be empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date of birth can not be empty.")
    @JsonFormat(lenient = OptBoolean.FALSE)
    private Date dateOfBirth;

    @NotEmpty(message = "Gender must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String gender;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date of hiring must not be empty.")
    private Date dateOfHiring;

    private boolean working;
    @Valid
    @NotNull(message = "You must provide an address.")
    private Address address;
    @Valid
    @NotNull(message = "You must provide an IDcard.")
    private IDcard idcard;

    private Department department;

    private Schedule schedule;


    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, @NotEmpty(message = "First name cannot be empty!") String firstName, @NotEmpty(message = "Last name must not be empty!") String lastName, @NotNull(message = "Date of birth can not be empty.") Date dateOfBirth, @NotEmpty(message = "Gender must not be empty.") String gender, @NotNull(message = "Date of hiring must not be empty.") Date dateOfHiring, boolean working, Address address, IDcard IDcard, Department department, Schedule schedule) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateOfHiring = dateOfHiring;
        this.working = working;
        this.address = address;
        this.idcard = IDcard;
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

    public Date getDateOfBirth() throws ParseException {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date minDate = sdf.parse("1955-01-01");
            Date maxDate = sdf.parse("2002-01-01");

            if (dateOfBirth.before(minDate)) {
                throw new InvalidDateException("Date " + dateOfBirth + " must be above " + minDate + " and though");
            }
            if (dateOfBirth.after(maxDate)) {
                throw new InvalidDateException("Date " + dateOfBirth + " must be lower than " + maxDate + " and though");
            }
            return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfHiring()  {
            dateOfHiring = new Date();
            return dateOfHiring;
    }

    public void setDateOfHiring(Date dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IDcard getIdcard() {
        return idcard;
    }

    public void setIdcard(IDcard idcard) {
        this.idcard = idcard;
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
