package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FlightService {
    String createFlights(@NonNull MultipartFile flights);
    String updateFlight(@NonNull FlightDto dto);
    String deleteFlight(@NonNull Long flightNumber);
    Flight getFlight(@NonNull Long flightNumber);
    List<Flight> getFlights();
}
