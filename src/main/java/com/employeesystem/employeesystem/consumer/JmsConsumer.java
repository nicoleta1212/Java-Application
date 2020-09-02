package com.employeesystem.employeesystem.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "employee", containerFactory = "myFactory", concurrency = "3-10")
    public void onMessage(String msg) {
        System.out.println("Received: " + msg);

    }
}

