package dev.xshoxruh.airline_reservation_system.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
