package com.example.myservice.controllers;

import com.example.myservice.entities.Car;
import com.example.myservice.entities.Customer;
import com.example.myservice.entities.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RentServiceRestTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    // ---- Root endpoint ----

    @Test
    public void testSayHello() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to the Rent Service API"));
    }

    // ---- Car endpoints ----

    @Test
    public void testAddCar() throws Exception {
        Car car = new Car("FR-100-AA", "Citroen", 11000.0, 2021);

        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plateNumber").value("FR-100-AA"))
                .andExpect(jsonPath("$.brand").value("Citroen"));
    }

    @Test
    public void testGetCars() throws Exception {
        Car car = new Car("FR-200-BB", "Dacia", 9000.0, 2020);
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)));

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCarByPlateNumber() throws Exception {
        Car car = new Car("FR-300-CC", "Fiat", 10500.0, 2023);
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)));

        mockMvc.perform(get("/cars/FR-300-CC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Fiat"))
                .andExpect(jsonPath("$.year").value(2023));
    }

    @Test
    public void testGetCarNotFound() throws Exception {
        mockMvc.perform(get("/cars/NONEXISTENT"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCar() throws Exception {
        Car car = new Car("FR-400-DD", "Opel", 13000.0, 2022);
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)));

        Car updated = new Car("FR-400-DD", "Opel", 14500.0, 2024);
        mockMvc.perform(put("/cars/FR-400-DD")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(14500.0))
                .andExpect(jsonPath("$.year").value(2024));
    }

    @Test
    public void testUpdateCarNotFound() throws Exception {
        Car updated = new Car("UNKNOWN", "Brand", 10000.0, 2020);
        mockMvc.perform(put("/cars/UNKNOWN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCar() throws Exception {
        Car car = new Car("FR-500-EE", "Volkswagen", 18000.0, 2023);
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)));

        mockMvc.perform(delete("/cars/FR-500-EE"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/cars/FR-500-EE"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCarNotFound() throws Exception {
        mockMvc.perform(delete("/cars/NONEXISTENT"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchCarsByBrand() throws Exception {
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Car("FR-600-FF", "BMW", 30000.0, 2024))));
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Car("FR-700-GG", "BMW", 35000.0, 2025))));

        mockMvc.perform(get("/cars/search").param("brand", "BMW"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // ---- Customer endpoints ----

    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer(0, "Claire Bernard", "claire@example.com", "0611111111");

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Claire Bernard"))
                .andExpect(jsonPath("$.email").value("claire@example.com"));
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new Customer(0, "David Moreau", "david@example.com", "0622222222"))));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCustomerNotFound() throws Exception {
        mockMvc.perform(get("/customers/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCustomerNotFound() throws Exception {
        mockMvc.perform(delete("/customers/9999"))
                .andExpect(status().isNotFound());
    }

    // ---- Rental endpoints ----

    @Test
    public void testAddRental() throws Exception {
        Rental rental = new Rental(0, "FR-100-AA", 1L, "2025-09-01", "2025-09-05", 200.0);

        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rental)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carPlateNumber").value("FR-100-AA"))
                .andExpect(jsonPath("$.totalCost").value(200.0));
    }

    @Test
    public void testGetRentals() throws Exception {
        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new Rental(0, "FR-200-BB", 2L, "2025-10-01", "2025-10-03", 120.0))));

        mockMvc.perform(get("/rentals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetRentalNotFound() throws Exception {
        mockMvc.perform(get("/rentals/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRentalsByCustomer() throws Exception {
        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new Rental(0, "FR-300-CC", 50L, "2025-11-01", "2025-11-04", 180.0))));

        mockMvc.perform(get("/rentals/customer/50"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelRentalNotFound() throws Exception {
        mockMvc.perform(delete("/rentals/9999"))
                .andExpect(status().isNotFound());
    }
}
