package com.example.myservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {

    @Test
    public void testDefaultConstructor() {
        Rental rental = new Rental();
        assertEquals(0, rental.getId());
        assertNull(rental.getCarPlateNumber());
        assertEquals(0, rental.getCustomerId());
        assertNull(rental.getStartDate());
        assertNull(rental.getEndDate());
        assertEquals(0.0, rental.getTotalCost());
    }

    @Test
    public void testParameterizedConstructor() {
        Rental rental = new Rental(1L, "FR-123-AB", 10L, "2025-06-01", "2025-06-07", 350.0);
        assertEquals(1L, rental.getId());
        assertEquals("FR-123-AB", rental.getCarPlateNumber());
        assertEquals(10L, rental.getCustomerId());
        assertEquals("2025-06-01", rental.getStartDate());
        assertEquals("2025-06-07", rental.getEndDate());
        assertEquals(350.0, rental.getTotalCost());
    }

    @Test
    public void testSetId() {
        Rental rental = new Rental();
        rental.setId(5L);
        assertEquals(5L, rental.getId());
    }

    @Test
    public void testSetCarPlateNumber() {
        Rental rental = new Rental();
        rental.setCarPlateNumber("FR-999-ZZ");
        assertEquals("FR-999-ZZ", rental.getCarPlateNumber());
    }

    @Test
    public void testSetCustomerId() {
        Rental rental = new Rental();
        rental.setCustomerId(7L);
        assertEquals(7L, rental.getCustomerId());
    }

    @Test
    public void testSetDates() {
        Rental rental = new Rental();
        rental.setStartDate("2025-07-01");
        rental.setEndDate("2025-07-10");
        assertEquals("2025-07-01", rental.getStartDate());
        assertEquals("2025-07-10", rental.getEndDate());
    }

    @Test
    public void testSetTotalCost() {
        Rental rental = new Rental();
        rental.setTotalCost(520.0);
        assertEquals(520.0, rental.getTotalCost());
    }

    @Test
    public void testToString() {
        Rental rental = new Rental(1L, "FR-123-AB", 10L, "2025-06-01", "2025-06-07", 350.0);
        String result = rental.toString();
        assertTrue(result.contains("FR-123-AB"));
        assertTrue(result.contains("2025-06-01"));
        assertTrue(result.contains("350.0"));
    }
}
