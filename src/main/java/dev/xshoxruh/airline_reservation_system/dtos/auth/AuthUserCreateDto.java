package dev.xshoxruh.airline_reservation_system.dtos.auth;

import dev.xshoxruh.airline_reservation_system.enummrations.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthUserCreateDto implements Serializable {
    @NotBlank
    String username;

    @Email
    @NotBlank
    String email;

    String firstName;

    String lastName;

    LocalDate dateOfBirth;

    @NotBlank
    String password;

    @NotBlank
    String confirmPassword;

    @NotBlank
    String citizenship;

    @NotNull
    Gender gender;
}