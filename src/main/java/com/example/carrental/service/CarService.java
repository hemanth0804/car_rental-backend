package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        // Update fields...existingCar.setBrand(updatedCar.getBrand());
        existingCar.setName(updatedCar.getName());
        existingCar.setType(updatedCar.getType());
        existingCar.setTransmission(updatedCar.getTransmission());
        existingCar.setColor(updatedCar.getColor());
        existingCar.setModelYear(updatedCar.getModelYear());
        existingCar.setPrice(updatedCar.getPrice());
        existingCar.setDescription(updatedCar.getDescription());
        existingCar.setImageUrl(updatedCar.getImageUrl());

        return carRepository.save(existingCar);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> searchCars(String brand, String type, String color, String transmission) {
        // Combine search criteria
        List<Car> cars = carRepository.findAll(); // Start with all cars
        if (brand != null && !brand.isEmpty()) {
            cars = cars.stream().filter(car -> car.getBrand().toLowerCase().contains(brand.toLowerCase())).collect(Collectors.toList());
        }
        if (type != null && !type.isEmpty()) {
            cars = cars.stream().filter(car -> car.getType().toLowerCase().contains(type.toLowerCase())).collect(Collectors.toList());
        }
        if (color != null && !color.isEmpty()) {
            cars = cars.stream().filter(car -> car.getColor().toLowerCase().contains(color.toLowerCase())).collect(Collectors.toList());
        }
        if (transmission != null && !transmission.isEmpty()) {
            cars = cars.stream().filter(car -> car.getTransmission().toLowerCase().contains(transmission.toLowerCase())).collect(Collectors.toList());
        }
        return cars;
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }
}