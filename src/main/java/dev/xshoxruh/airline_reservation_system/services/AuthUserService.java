package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AuthUserCreateDto;
import dev.xshoxruh.airline_reservation_system.dtos.auth.Login;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthUserService {
    String register(@NonNull AuthUserCreateDto dto);

    String login(@NonNull Login login);

    String activaceAccount(@NonNull String code);

    List<Airport> getCity(@NonNull String city);
    Airport getAirport(@NonNull Long id);
}
