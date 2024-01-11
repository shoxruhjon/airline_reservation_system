package dev.xshoxruh.airline_reservation_system.dtos.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto implements Serializable {
    @NotNull
    Long flightNumber;
    @NotBlank
    String departureFrom;
    @NotBlank
    String arrivalTo;
    @NotNull
    LocalDateTime departureDateTime;
    @NotNull
    LocalDateTime arrivalDateTime;
    @NotNull
    Integer availableSeats;
    @NotNull
    BigDecimal ticketPrice;
}