package dev.xshoxruh.airline_reservation_system.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport extends Auditable{

    @Column(nullable = false, unique = true)
    private String airportName;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.PERSIST)
    private List<Flight> flights;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Agent> agents = new ArrayList<>();

}
