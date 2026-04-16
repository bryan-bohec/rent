package com.example.myservice.services;

import com.example.myservice.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Customer addCustomer(Customer customer) {
        customer.setId(idCounter.getAndIncrement());
        customers.add(customer);
        return customer;
    }

    public Customer getCustomer(long id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public boolean removeCustomer(long id) {
        return customers.removeIf(c -> c.getId() == id);
    }

    public Customer findByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public long count() {
        return customers.size();
    }
}
