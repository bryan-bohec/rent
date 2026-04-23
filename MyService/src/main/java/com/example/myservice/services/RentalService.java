package com.example.myservice.services;

import com.example.myservice.entities.Rental;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final List<Rental> rentals = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Rental addRental(Rental rental) {
        rental.setId(idCounter.getAndIncrement());
        rentals.add(rental);
        return rental;
    }

    public Rental getRental(long id) {
        return rentals.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }

    public List<Rental> getRentalsByCustomer(long customerId) {
        return rentals.stream()
                .filter(r -> r.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    public List<Rental> getRentalsByCar(String carPlateNumber) {
        return rentals.stream()
                .filter(r -> r.getCarPlateNumber().equals(carPlateNumber))
                .collect(Collectors.toList());
    }

    public boolean cancelRental(long id) {
        return rentals.removeIf(r -> r.getId() == id);
    }

    public long count() {
        return rentals.size();
    }
}
