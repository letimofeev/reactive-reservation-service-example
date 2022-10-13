package com.example.reservationservice.repository;

import com.example.reservationservice.entity.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
}
