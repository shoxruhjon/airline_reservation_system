package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Flight f set f.departureFrom = ?1, f.arrivalTo = ?2, 
            f.departureDateTime = ?3, f.arrivalDateTime = ?4, 
            f.availableSeats = ?5, f.ticketPrice = ?6,
            f.updatedBy = ?7 where f.flightNumber = ?8""")
    int update(String departureFrom, String arrivalTo,
               LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
               Integer availableSeats, BigDecimal ticketPrice,
               Long updatedBy, Long flightNumber);

    @Query("select f from Flight f where f.flightNumber = ?1")
    Optional<Flight> findByFlightNumber(Long flightNumber);

    @Transactional
    @Modifying
    @Query("delete from Flight f where f.flightNumber = ?1")
    void deleteByFlightNumber(Long flightNumber);
}