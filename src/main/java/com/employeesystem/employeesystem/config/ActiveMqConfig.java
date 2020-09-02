package com.employeesystem.employeesystem.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.util.Arrays;

@Component
public class ActiveMqConfig {

    @Value("${jsa.activemq.broker.url}")
    private
    String brokerUrl;

    @Value("${jsa.activemq.borker.username}")
    private
    String userName;

    @Value("${jsa.activemq.borker.password}")
    private
    String password;


    //Initial ConnectionFactory

    @Bean
    @Primary
    private ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setTrustedPackages(Arrays.asList("java.lang","com.employeesystem.employeesystem"));
        connectionFactory.getPrefetchPolicy().setQueuePrefetch(1);
        RedeliveryPolicy redeliveryPolicy = connectionFactory.getRedeliveryPolicy();
        redeliveryPolicy.setUseExponentialBackOff(true);
        redeliveryPolicy.setMaximumRedeliveries(4);
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        cachingConnectionFactory.setCacheConsumers(true);
        cachingConnectionFactory.setCacheProducers(true);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    @Bean // Serialize message content to json using TextMessage
    private MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
       //return new SimpleMessageConverter();
    }


    // * Used for Receiving Message

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }


    //* Used for Sending Messages.

    @Bean
    @Primary // default jmsTamplate accross the application
    public JmsTemplate queueJmsTemplate(@Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setMessageConverter(jacksonJmsMessageConverter());
       // template.setDestinationResolver(new DynamicDestinationResolver()); // allows the template to look up the destinations dynamically based on the name
        template.setDefaultDestinationName("employee");
        template.setPriority(4);
        template.setTimeToLive(30000L);
        template.setExplicitQosEnabled(true);
        return template;
    }

    @Bean
    public JmsTemplate topicJmsTemplate(@Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setDestinationResolver(new DynamicDestinationResolver()); // allows the template to look up the destinations dynamically based on the name
        template.setPubSubDomain(true);
        return template;
    }
}

