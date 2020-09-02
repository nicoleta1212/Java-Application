package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.employee.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressDTO  implements Serializable {
    private String id;
    private String region;
    private String city;
    private String street;
    private int nr;
    private String block;
    private char staircase;
    private int apartment;
    private List<Employee> employees = new ArrayList<>();

    public AddressDTO() {
    }

    public AddressDTO(String id, String region, String city, String street, int nr, String block, char staircase, int apartment) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.nr = nr;
        this.block = block;
        this.staircase = staircase;
        this.apartment = apartment;
    }

    public AddressDTO(String id, String region, String city, String street, int nr) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.nr = nr;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public char getStaircase() {
        return staircase;
    }

    public void setStaircase(char staircase) {
        this.staircase = staircase;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
