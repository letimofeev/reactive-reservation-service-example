package com.example.reservationclient.client;

import com.example.reservationclient.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

    private final WebClient webClient;

    public Flux<Reservation> getAllReservations() {
        return webClient
                .get()
                .uri("http://localhost:8080/reservations")
                .retrieve()
                .bodyToFlux(Reservation.class);
    }
}
