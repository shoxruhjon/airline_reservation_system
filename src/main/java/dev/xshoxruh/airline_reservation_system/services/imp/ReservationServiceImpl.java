package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.config.security.SessionUser;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import dev.xshoxruh.airline_reservation_system.entities.Reservation;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserRepository;
import dev.xshoxruh.airline_reservation_system.repositories.FlightRepository;
import dev.xshoxruh.airline_reservation_system.repositories.ReservationRepository;
import dev.xshoxruh.airline_reservation_system.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final SessionUser sessionUser;
    private final AuthUserRepository authUserRepository;

    @Override
    public Reservation reserve(@NonNull Long flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).
                orElseThrow(() -> new RuntimeException("Not Found Flight."));
        Integer lastSeatNumber = reservationRepository.findByFlight_Id(flight.getId());
        Reservation reservation = new Reservation();
        AuthUser user = authUserRepository.findById(sessionUser.id())
                .orElseThrow(() -> new RuntimeException("Not Found User"));
        if(lastSeatNumber <= flight.getAvailableSeats())
            reservation.setSeatNumber(++lastSeatNumber);
        else
            throw new RuntimeException("It is full");
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setFlight(flight);
        reservation.setAuthUser(user);
        reservation.setCreatedAt(LocalDateTime.now());
        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public String cancelReservation(@NonNull Integer seatNumber) {
        Reservation reservation = reservationRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        reservationRepository.updateDeletedBySeatNumber(true, seatNumber);
        return "Canceled";
    }
}
