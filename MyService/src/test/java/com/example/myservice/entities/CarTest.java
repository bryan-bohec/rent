package com.example.myservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testDefaultConstructor() {
        Car car = new Car();
        assertNull(car.getPlateNumber());
        assertNull(car.getBrand());
        assertEquals(0.0, car.getPrice());
        assertEquals(0, car.getYear());
    }

    @Test
    public void testParameterizedConstructor() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        assertEquals("FR-123-AB", car.getPlateNumber());
        assertEquals("Renault", car.getBrand());
        assertEquals(12000.0, car.getPrice());
        assertEquals(2022, car.getYear());
    }

    @Test
    public void testSetPlateNumber() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        car.setPlateNumber("FR-999-ZZ");
        assertEquals("FR-999-ZZ", car.getPlateNumber());
    }

    @Test
    public void testSetBrand() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        car.setBrand("Peugeot");
        assertEquals("Peugeot", car.getBrand());
    }

    @Test
    public void testSetPrice() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        car.setPrice(15500.0);
        assertEquals(15500.0, car.getPrice());
    }

    @Test
    public void testSetYear() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        car.setYear(2024);
        assertEquals(2024, car.getYear());
    }

    @Test
    public void testToString() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        String result = car.toString();
        assertTrue(result.contains("FR-123-AB"));
        assertTrue(result.contains("Renault"));
        assertTrue(result.contains("12000.0"));
        assertTrue(result.contains("2022"));
    }
}
