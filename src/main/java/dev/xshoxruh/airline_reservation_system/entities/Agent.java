package dev.xshoxruh.airline_reservation_system.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Agent extends Auditable{

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String agentName;

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column(nullable = false)
    private String role;

    @Column
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

}

