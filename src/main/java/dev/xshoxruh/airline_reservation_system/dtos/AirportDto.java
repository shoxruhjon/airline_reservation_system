package dev.xshoxruh.airline_reservation_system.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportDto implements Serializable {
    @NotBlank
    String airportName;
    @NotBlank
    String city;
    @NotBlank
    String country;
}