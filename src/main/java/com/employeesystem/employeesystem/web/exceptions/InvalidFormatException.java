package com.employeesystem.employeesystem.web.exceptions;

import com.sun.media.sound.InvalidDataException;
import org.springframework.http.HttpStatus;

public class InvalidFormatException extends InvalidDataException {
    private String name;
    private HttpStatus status = HttpStatus.FORBIDDEN;

    public InvalidFormatException(String name) {
        this.name = name;
    }

    public InvalidFormatException(String s, String name) {
        super(s);
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

