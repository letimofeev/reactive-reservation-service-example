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

    private final ReservationRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Flux<Reservation> reservationFlux = Flux
                .just("Alex", "Lexa", "Oleg", "Nikita", "Slava", "Sergay")
                .map(name -> new Reservation(null, name))
                .flatMap(repository::save);
        repository.deleteAll()
                .thenMany(reservationFlux)
                .thenMany(repository.findAll())
                .subscribe(System.out::println);
    }
}
