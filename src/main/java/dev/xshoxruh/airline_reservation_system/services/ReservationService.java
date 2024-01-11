package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Reservation;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public interface ReservationService {

    Reservation reserve(@NonNull Long flightNumber);

    String cancelReservation(@NonNull Integer seatNumber);
}
