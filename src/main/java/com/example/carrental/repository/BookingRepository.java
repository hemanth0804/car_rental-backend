package com.example.carrental.repository;

import com.example.carrental.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(Long userId);
    List<Booking> findByCar(Long carId);
    List<Booking> findByUserId(Long userId);
}
