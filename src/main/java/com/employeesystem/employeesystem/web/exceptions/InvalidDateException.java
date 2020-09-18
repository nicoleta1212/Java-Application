package com.employeesystem.employeesystem.web.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDateException extends RuntimeException {
    private String name;

    private HttpStatus status = HttpStatus.FORBIDDEN;

    public InvalidDateException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
