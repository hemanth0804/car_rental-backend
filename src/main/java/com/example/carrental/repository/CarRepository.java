package com.example.carrental.repository;

import com.example.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandContainingIgnoreCase(String brand);
    List<Car> findByTypeContainingIgnoreCase(String type);
    List<Car> findByColorContainingIgnoreCase(String color);
    List<Car> findByTransmissionContainingIgnoreCase(String transmission);
}