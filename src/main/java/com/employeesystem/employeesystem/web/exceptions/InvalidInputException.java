package com.employeesystem.employeesystem.web.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException {
    private String name;
    private HttpStatus status = HttpStatus.FORBIDDEN;

    public InvalidInputException(String name, HttpStatus status) {
        this.name = name;
        this.status = status;
    }

    public InvalidInputException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
