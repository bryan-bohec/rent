package com.example.myservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertEquals(0, customer.getId());
        assertNull(customer.getName());
        assertNull(customer.getEmail());
        assertNull(customer.getPhone());
    }

    @Test
    public void testParameterizedConstructor() {
        Customer customer = new Customer(1L, "Alice Dupont", "alice@example.com", "0612345678");
        assertEquals(1L, customer.getId());
        assertEquals("Alice Dupont", customer.getName());
        assertEquals("alice@example.com", customer.getEmail());
        assertEquals("0612345678", customer.getPhone());
    }

    @Test
    public void testSetId() {
        Customer customer = new Customer();
        customer.setId(42L);
        assertEquals(42L, customer.getId());
    }

    @Test
    public void testSetName() {
        Customer customer = new Customer();
        customer.setName("Bob Martin");
        assertEquals("Bob Martin", customer.getName());
    }

    @Test
    public void testSetEmail() {
        Customer customer = new Customer();
        customer.setEmail("bob@example.com");
        assertEquals("bob@example.com", customer.getEmail());
    }

    @Test
    public void testSetPhone() {
        Customer customer = new Customer();
        customer.setPhone("0698765432");
        assertEquals("0698765432", customer.getPhone());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(1L, "Alice Dupont", "alice@example.com", "0612345678");
        String result = customer.toString();
        assertTrue(result.contains("Alice Dupont"));
        assertTrue(result.contains("alice@example.com"));
        assertTrue(result.contains("0612345678"));
    }
}
