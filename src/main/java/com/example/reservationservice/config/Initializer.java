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
        Flux<String> names = Flux.just("Alex", "Lexa", "Oleg", "Nikita", "Slava", "Sergay");
        Flux<Reservation> reservationFlux = names.map(name -> new Reservation(null, name));
        Flux<Reservation> saved = reservationFlux.flatMap(repository::save);
        saved.subscribe(System.out::println);
    }
}
