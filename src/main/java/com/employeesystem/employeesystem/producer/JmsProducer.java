package com.employeesystem.employeesystem.producer;

import com.employeesystem.employeesystem.repository.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Autowired
    private
    JmsTemplate jmsTemplate;

    @Value("${jsa.activemq.queue}")
    private
    String queue;

    public void send(Employee employee){
        jmsTemplate.convertAndSend(queue, "New employee from EmployeeSystem: " + employee);
    }
}
