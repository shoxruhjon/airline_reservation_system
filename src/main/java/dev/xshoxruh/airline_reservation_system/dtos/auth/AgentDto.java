package dev.xshoxruh.airline_reservation_system.dtos.auth;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AgentDto implements Serializable {
    @NotBlank
    String username;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotBlank
    String confirmPassword;

    @NotBlank
    String agentName;
}