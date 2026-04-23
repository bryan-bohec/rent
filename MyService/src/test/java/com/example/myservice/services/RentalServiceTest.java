package com.example.myservice.services;

import com.example.myservice.entities.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {

    private RentalService rentalService;

    @BeforeEach
    public void setUp() {
        rentalService = new RentalService();
    }

    @Test
    public void testAddRental() {
        Rental rental = new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0);
        Rental created = rentalService.addRental(rental);
        assertTrue(created.getId() > 0);
        assertEquals(1, rentalService.count());
    }

    @Test
    public void testGetRental() {
        Rental created = rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        Rental found = rentalService.getRental(created.getId());
        assertNotNull(found);
        assertEquals("FR-123-AB", found.getCarPlateNumber());
    }

    @Test
    public void testGetRentalNotFound() {
        Rental result = rentalService.getRental(999L);
        assertNull(result);
    }

    @Test
    public void testGetRentals() {
        rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        rentalService.addRental(new Rental(0, "FR-456-CD", 2L, "2025-07-01", "2025-07-05", 200.0));
        List<Rental> rentals = rentalService.getRentals();
        assertEquals(2, rentals.size());
    }

    @Test
    public void testGetRentalsByCustomer() {
        rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        rentalService.addRental(new Rental(0, "FR-456-CD", 2L, "2025-07-01", "2025-07-05", 200.0));
        rentalService.addRental(new Rental(0, "FR-789-EF", 1L, "2025-08-01", "2025-08-03", 150.0));
        List<Rental> customer1Rentals = rentalService.getRentalsByCustomer(1L);
        assertEquals(2, customer1Rentals.size());
    }

    @Test
    public void testGetRentalsByCar() {
        rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        rentalService.addRental(new Rental(0, "FR-123-AB", 2L, "2025-07-01", "2025-07-05", 250.0));
        rentalService.addRental(new Rental(0, "FR-456-CD", 3L, "2025-08-01", "2025-08-03", 150.0));
        List<Rental> carRentals = rentalService.getRentalsByCar("FR-123-AB");
        assertEquals(2, carRentals.size());
    }

    @Test
    public void testCancelRental() {
        Rental created = rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        assertTrue(rentalService.cancelRental(created.getId()));
        assertEquals(0, rentalService.count());
    }

    @Test
    public void testCancelRentalNotFound() {
        assertFalse(rentalService.cancelRental(999L));
    }

    @Test
    public void testCount() {
        assertEquals(0, rentalService.count());
        rentalService.addRental(new Rental(0, "FR-123-AB", 1L, "2025-06-01", "2025-06-07", 350.0));
        assertEquals(1, rentalService.count());
    }
}
