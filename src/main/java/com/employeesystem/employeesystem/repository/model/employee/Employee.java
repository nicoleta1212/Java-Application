package com.employeesystem.employeesystem.repository.model.employee;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.repository.model.Schedule.Schedule;
import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.repository.model.department.Department;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Validated
@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)
public class Employee implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty!")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date of birth can not be empty.")
    @JsonFormat(lenient = OptBoolean.FALSE)
    private Date dateOfBirth;

    @NotEmpty(message = "Gender must not be empty.")
    private String gender;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date of hiring must not be empty.")
    private Date dateOfHiring;

    private boolean working;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_FK")
    @NotNull(message = "You must provide an address.")
    @Valid
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDcard_FK")
    @NotNull(message = "You must provide an IDcard.")
    @Valid
    private IDcard idcard;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
    @JoinColumn(name = "schedule_FK")
    @JoinTable(name = "employee_schedule", catalog = "employeedb", joinColumns = {@JoinColumn(name = "employee_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "schedule_id", nullable = false, updatable = false)})
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_FK")
    @JsonIgnoreProperties("employee")
    private Department department;

    public Employee() {
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

    public Date getDateOfBirth()  {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)  {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(Date dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public IDcard getIdcard() {
        return idcard;
    }

    public void setIdcard(IDcard idcard) {
        this.idcard = idcard;
    }

    public Address getAddress()  {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Employee:" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", dateOfHiring=" + dateOfHiring +
                ", working=" + working +
                ", address=" + address +
                ", IDcard=" + idcard +
                ", schedule=" + schedule +
                ", department=" + department +
                '.';
    }
}
