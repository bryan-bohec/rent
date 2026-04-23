package com.example.myservice.services;

import com.example.myservice.entities.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public Car getCar(String plateNumber) {
        return cars.stream()
                .filter(car -> car.getPlateNumber().equals(plateNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public boolean removeCar(String plateNumber) {
        return cars.removeIf(car -> car.getPlateNumber().equals(plateNumber));
    }

    public Car updateCar(String plateNumber, Car updated) {
        Car existing = getCar(plateNumber);
        if (existing == null) {
            return null;
        }
        existing.setBrand(updated.getBrand());
        existing.setPrice(updated.getPrice());
        existing.setYear(updated.getYear());
        return existing;
    }

    public List<Car> searchByBrand(String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public long count() {
        return cars.size();
    }
}
