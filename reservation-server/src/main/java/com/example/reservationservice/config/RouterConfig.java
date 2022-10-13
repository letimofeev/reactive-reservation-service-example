package com.example.reservationservice.config;

import com.example.reservationservice.entity.Reservation;
import com.example.reservationservice.repository.ReservationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ReservationRepository repository) {
        return RouterFunctions.route(RequestPredicates.GET("/reservations"),
                request -> ServerResponse.ok().body(repository.findAll(), Reservation.class));
    }
}
