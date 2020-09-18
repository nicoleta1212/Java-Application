package com.employeesystem.employeesystem.web.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {
    private String name;
    private String id;
    private HttpStatus status = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public EntityNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
