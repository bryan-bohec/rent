package com.example.myservice.controllers;

import com.example.myservice.entities.Car;
import com.example.myservice.entities.Customer;
import com.example.myservice.entities.Rental;
import com.example.myservice.services.CarService;
import com.example.myservice.services.CustomerService;
import com.example.myservice.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentServiceRest {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RentalService rentalService;

    @GetMapping("/")
    public String sayHello() {
        return "Welcome to the Rent Service API";
    }

    // ---- Car endpoints ----

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        carService.addCar(car);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/cars/{plateNumber}")
    public ResponseEntity<Car> getCar(@PathVariable String plateNumber) {
        Car car = carService.getCar(plateNumber);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PutMapping("/cars/{plateNumber}")
    public ResponseEntity<Car> updateCar(@PathVariable String plateNumber, @RequestBody Car car) {
        Car updated = carService.updateCar(plateNumber, car);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/cars/{plateNumber}")
    public ResponseEntity<Void> deleteCar(@PathVariable String plateNumber) {
        boolean removed = carService.removeCar(plateNumber);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cars/search")
    public List<Car> searchCarsByBrand(@RequestParam String brand) {
        return carService.searchByBrand(brand);
    }

    // ---- Customer endpoints ----

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer created = customerService.addCustomer(customer);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        boolean removed = customerService.removeCustomer(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // ---- Rental endpoints ----

    @PostMapping("/rentals")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rental) {
        Rental created = rentalService.addRental(rental);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/rentals")
    public List<Rental> getRentals() {
        return rentalService.getRentals();
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable long id) {
        Rental rental = rentalService.getRental(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/rentals/customer/{customerId}")
    public List<Rental> getRentalsByCustomer(@PathVariable long customerId) {
        return rentalService.getRentalsByCustomer(customerId);
    }

    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<Void> cancelRental(@PathVariable long id) {
        boolean removed = rentalService.cancelRental(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
