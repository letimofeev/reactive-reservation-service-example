package com.example.reservationservice.config;

import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

@Configuration
@RequiredArgsConstructor
public class Initializer {

    private final ReservationRepository reservationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Flux<Reservation> reservationFlux = Flux
                .just("Alex", "Lexa", "Oleg", "Nikita", "Slava", "Sergay")
                .map(name -> new Reservation(null, name))
                .flatMap(reservationRepository::save);
        reservationRepository.deleteAll()
                .thenMany(reservationFlux)
                .thenMany(reservationRepository.findAll())
                .subscribe(System.out::println);
    }
}
