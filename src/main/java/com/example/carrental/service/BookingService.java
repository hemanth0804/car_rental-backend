package com.example.carrental.service;

import com.example.carrental.model.Booking;
import com.example.carrental.model.Car;
import com.example.carrental.model.User;
import com.example.carrental.repository.BookingRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(Booking booking) {
        // Always fetch car and user from DB to avoid foreign key issues
        if (booking.getCar() != null && booking.getCar().getId() != null) {
            Car car = carRepository.findById(booking.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + booking.getCar().getId()));
            booking.setCar(car);
        } else {
            throw new RuntimeException("Car ID is required");
        }
        if (booking.getUser() != null && booking.getUser().getId() != null) {
            User user = userRepository.findById(booking.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + booking.getUser().getId()));
            booking.setUser(user);
        } else {
            throw new RuntimeException("User ID is required");
        }
        // Set status if not set
        if (booking.getStatus() == null) {
            booking.setStatus("pending");
        }
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(existing -> {
            existing.setPickupDate(updatedBooking.getPickupDate());
            existing.setReturnDate(updatedBooking.getReturnDate());
            existing.setTotalPrice(updatedBooking.getTotalPrice());
            existing.setStatus(updatedBooking.getStatus());
            // Optionally update car/user
            if (updatedBooking.getCar() != null) existing.setCar(updatedBooking.getCar());
            if (updatedBooking.getUser() != null) existing.setUser(updatedBooking.getUser());
            return bookingRepository.save(existing);
        }).orElse(null);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
} 