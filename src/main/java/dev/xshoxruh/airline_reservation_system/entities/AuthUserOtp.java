package dev.xshoxruh.airline_reservation_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AuthUserOtp extends Auditable{

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Long userID;

    @Future
    @Column(nullable = false)
    private LocalDateTime expiresAt;
}
