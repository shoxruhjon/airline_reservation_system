package dev.xshoxruh.airline_reservation_system.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Flight extends Auditable{

    @Column
    private Long flightNumber;

    @Column
    private String departureFrom;

    @Column
    private String arrivalTo;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS")
    private LocalDateTime departureDateTime;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS")
    private LocalDateTime arrivalDateTime;

    @Column
    private Integer availableSeats;

    @Column
    private BigDecimal ticketPrice;

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column
    @LastModifiedBy
    private Long updatedBy;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;
}
