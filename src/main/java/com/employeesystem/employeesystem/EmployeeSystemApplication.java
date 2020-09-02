package com.employeesystem.employeesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ComponentScan
public class EmployeeSystemApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(EmployeeSystemApplication.class, args);
	}

}
