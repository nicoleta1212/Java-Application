package com.employeesystem.employeesystem.repository.model.address;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "address")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Address.class)
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty(message = "Region must not be empty.")
    @Pattern(regexp = "^[a-zA-Z_ -]+$", message = "Wrong input: please use characters!")
    private String region;

    @NotEmpty(message = "City must not be empty.")
    @Pattern(regexp = "^[a-zA-Z_ -]+$", message = "Wrong input: please use characters!")
    private String city;

    @NotEmpty(message = "Street must not be empty.")
    private String street;

    @NotNull(message = "Number of street must not be empty.")
    @Min(value = 1, message = "You are allowed only positive numbers and bigger than 0.")
    private int nr;

    private String block;

    private char staircase;

    @Min(value = 1, message = "You are allowed only positive numbers and bigger than 0.")
    private int  apartment;

    public Address() {
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

    @Override
    public String toString() {
        return "Address:" +
                "id='" + id + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", nr=" + nr +
                ", block='" + block + '\'' +
                ", staircase=" + staircase +
                ", apartment=" + apartment +
                '.';
    }
}
