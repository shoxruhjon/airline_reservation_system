package dev.xshoxruh.airline_reservation_system.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation extends Auditable {

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "auth_user_id", nullable = false, unique = true)
    private AuthUser authUser;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column
    private LocalDateTime reservationDate;

    @Column
    private Integer seatNumber;
}
