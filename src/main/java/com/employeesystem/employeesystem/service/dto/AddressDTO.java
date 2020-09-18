package com.employeesystem.employeesystem.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class AddressDTO  implements Serializable {
    private String id;
    @NotEmpty(message = "Region must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String region;

    @NotEmpty(message = "City must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Wrong input: please use characters!")
    private String city;

    @NotEmpty(message = "Street must not be empty.")
    private String street;

    @NotNull(message = "Number of street must not be empty.")
    @Min(value = 1, message = "You are allowed only positive numbers and bigger than 0.")
    private int nr;

    private String block;

    private char staircase;

    @Min(value = 1, message = "You are allowed only positive numbers and bigger than 0.")
    private int apartment;



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
       // if(Character.toString(staircase).matches("^[a-zA-Z]+$")){
            return staircase;
      /*  }else {
            throw new InvalidInputException(Character.toString(staircase));
        }*/

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


}
