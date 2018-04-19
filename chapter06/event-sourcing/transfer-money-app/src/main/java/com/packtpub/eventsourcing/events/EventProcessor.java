package com.packtpub.eventsourcing.events;

import com.packtpub.eventsourcing.customer.events.AccountCreatedEvent;
import com.packtpub.eventsourcing.customer.events.CustomerCreatedEvent;
import com.packtpub.eventsourcing.customer.state.Account;
import com.packtpub.eventsourcing.customer.state.Customer;
import com.packtpub.eventsourcing.customer.state.persistence.AccountRepository;
import com.packtpub.eventsourcing.customer.state.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventProcessor {

    public void process(Event event) {
        if (CustomerCreatedEvent.NAME.equals(event.getEventName())) {
            Customer customer = new Customer(event);
            getRepository(event.getEventName()).save(customer);
        } else if (AccountCreatedEvent.NAME.equals(event.getEventName())) {
            Account account = new Account(event);
            getRepository(event.getEventName()).save(account);
        }
        System.out.println(getRepository(event.getEventName()).findAll().size());
    }

    private JpaRepository getRepository(String eventName) {
        Map<String, JpaRepository> repositoryMap = new HashMap<>();
        repositoryMap.put(CustomerCreatedEvent.NAME, customerRepository);
        repositoryMap.put(AccountCreatedEvent.NAME, accountRepository);
        return repositoryMap.get(eventName);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
}
