package com.employeesystem.employeesystem.web;

public class SearchRequest  {
     public String departName;
    public String city ;

    public String start;

    public String end;

    public SearchRequest(String departName, String city, String start, String end) {
        this.departName = departName;
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public SearchRequest() {
    }

    public String getDepartName() {
        return departName ;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
