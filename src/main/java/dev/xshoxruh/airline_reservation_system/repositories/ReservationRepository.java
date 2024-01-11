package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.reservationDate = ?1")
    List<Reservation> findByReservationDate(LocalDateTime reservationDate);

    @Query("select r from Reservation r where r.flight.id = ?1 order by r.seatNumber")
    List<Reservation> findByFlight_IdOrderBySeatNumberAsc(Long id);

    @Query("select MAX(r.seatNumber) from Reservation r where r.flight.id = ?1")
    Integer findByFlight_Id(Long id);

    @Query("select r from Reservation r where r.seatNumber = ?1")
    Optional<Reservation> findBySeatNumber(Integer seatNumber);

    @Transactional
    @Modifying
    @Query("update Reservation r set r.deleted = ?1 where r.seatNumber = ?2")
    void updateDeletedBySeatNumber(boolean deleted, Integer seatNumber);
}