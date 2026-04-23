package com.example.myservice.services;

import com.example.myservice.entities.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService();
    }

    @Test
    public void testAddCar() {
        Car car = new Car("FR-123-AB", "Renault", 12000.0, 2022);
        carService.addCar(car);
        assertEquals(1, carService.count());
    }

    @Test
    public void testGetCars() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        carService.addCar(new Car("FR-456-CD", "Peugeot", 14000.0, 2023));
        List<Car> cars = carService.getCars();
        assertEquals(2, cars.size());
    }

    @Test
    public void testGetCarByPlateNumber() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        Car result = carService.getCar("FR-123-AB");
        assertNotNull(result);
        assertEquals("Renault", result.getBrand());
        assertEquals(2022, result.getYear());
    }

    @Test
    public void testGetCarNotFound() {
        Car result = carService.getCar("UNKNOWN");
        assertNull(result);
    }

    @Test
    public void testRemoveCar() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        assertTrue(carService.removeCar("FR-123-AB"));
        assertEquals(0, carService.count());
    }

    @Test
    public void testRemoveCarNotFound() {
        assertFalse(carService.removeCar("UNKNOWN"));
    }

    @Test
    public void testUpdateCar() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        Car updateData = new Car("FR-123-AB", "Renault", 13500.0, 2023);
        Car updated = carService.updateCar("FR-123-AB", updateData);
        assertNotNull(updated);
        assertEquals(13500.0, updated.getPrice());
        assertEquals(2023, updated.getYear());
    }

    @Test
    public void testUpdateCarNotFound() {
        Car updateData = new Car("UNKNOWN", "Brand", 10000.0, 2020);
        Car result = carService.updateCar("UNKNOWN", updateData);
        assertNull(result);
    }

    @Test
    public void testSearchByBrand() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        carService.addCar(new Car("FR-456-CD", "Peugeot", 14000.0, 2023));
        carService.addCar(new Car("FR-789-EF", "Renault", 16000.0, 2024));
        List<Car> renaults = carService.searchByBrand("Renault");
        assertEquals(2, renaults.size());
    }

    @Test
    public void testSearchByBrandCaseInsensitive() {
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        List<Car> result = carService.searchByBrand("renault");
        assertEquals(1, result.size());
    }

    @Test
    public void testCount() {
        assertEquals(0, carService.count());
        carService.addCar(new Car("FR-123-AB", "Renault", 12000.0, 2022));
        carService.addCar(new Car("FR-456-CD", "Peugeot", 14000.0, 2023));
        assertEquals(2, carService.count());
    }
}
