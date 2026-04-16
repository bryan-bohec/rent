package com.example.myservice.entities;

public class Car {
    private String plateNumber;
    private String brand;
    private double price;
    private int year;

    public Car() {
    }

    public Car(String plateNumber, String brand, double price, int year) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber='" + plateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}
