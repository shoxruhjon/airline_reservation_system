package dev.xshoxruh.airline_reservation_system.entities;

import dev.xshoxruh.airline_reservation_system.enummrations.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AuthUser extends Auditable{

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String password;

    @Column
    private String citizenship;

    @Enumerated
    private Gender gender;

    @Column(nullable = false)
    private String role;

    private boolean active;
}

