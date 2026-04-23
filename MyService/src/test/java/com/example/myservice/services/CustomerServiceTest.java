package com.example.myservice.services;

import com.example.myservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerService();
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(0, "Alice Dupont", "alice@example.com", "0612345678");
        Customer created = customerService.addCustomer(customer);
        assertTrue(created.getId() > 0);
        assertEquals(1, customerService.count());
    }

    @Test
    public void testAutoIncrementId() {
        Customer c1 = customerService.addCustomer(new Customer(0, "Alice", "alice@example.com", "0600000001"));
        Customer c2 = customerService.addCustomer(new Customer(0, "Bob", "bob@example.com", "0600000002"));
        assertTrue(c2.getId() > c1.getId());
    }

    @Test
    public void testGetCustomer() {
        Customer created = customerService.addCustomer(new Customer(0, "Alice Dupont", "alice@example.com", "0612345678"));
        Customer found = customerService.getCustomer(created.getId());
        assertNotNull(found);
        assertEquals("Alice Dupont", found.getName());
    }

    @Test
    public void testGetCustomerNotFound() {
        Customer result = customerService.getCustomer(999L);
        assertNull(result);
    }

    @Test
    public void testGetCustomers() {
        customerService.addCustomer(new Customer(0, "Alice", "alice@example.com", "0600000001"));
        customerService.addCustomer(new Customer(0, "Bob", "bob@example.com", "0600000002"));
        List<Customer> customers = customerService.getCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void testRemoveCustomer() {
        Customer created = customerService.addCustomer(new Customer(0, "Alice", "alice@example.com", "0600000001"));
        assertTrue(customerService.removeCustomer(created.getId()));
        assertEquals(0, customerService.count());
    }

    @Test
    public void testRemoveCustomerNotFound() {
        assertFalse(customerService.removeCustomer(999L));
    }

    @Test
    public void testFindByEmail() {
        customerService.addCustomer(new Customer(0, "Alice", "alice@example.com", "0600000001"));
        Customer found = customerService.findByEmail("alice@example.com");
        assertNotNull(found);
        assertEquals("Alice", found.getName());
    }

    @Test
    public void testFindByEmailCaseInsensitive() {
        customerService.addCustomer(new Customer(0, "Alice", "alice@example.com", "0600000001"));
        Customer found = customerService.findByEmail("ALICE@EXAMPLE.COM");
        assertNotNull(found);
    }

    @Test
    public void testFindByEmailNotFound() {
        Customer found = customerService.findByEmail("unknown@example.com");
        assertNull(found);
    }
}
