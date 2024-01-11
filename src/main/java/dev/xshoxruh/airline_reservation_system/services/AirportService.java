package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.dtos.AirportDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AirportService {

    String createAirport(@NonNull AirportDto dto);
    String updateAirport(@NonNull AirportDto dto);
    String deleteAirport(@NonNull Long id);
    List<Airport> getAll();
    Airport getById(@NonNull Long id);
}
