package dev.xshoxruh.airline_reservation_system.controllers;

import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import dev.xshoxruh.airline_reservation_system.services.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/agent")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'AGENT')")
public class AgentController {


    private final FlightService flightService;

    @PostMapping("/flights")
    public ResponseEntity<String> createFight(@Valid @RequestBody MultipartFile flights){
        return ResponseEntity.ok(flightService.createFlights(flights));
    }

    @PutMapping("/flight")
    public ResponseEntity<String> updateFight(@Valid @RequestBody FlightDto dto){
        return ResponseEntity.ok(flightService.updateFlight(dto));
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlights(){
        return ResponseEntity.ok(flightService.getFlights());
    }

    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long flightNumber){
        return ResponseEntity.ok(flightService.getFlight(flightNumber));
    }

    @DeleteMapping("/flight/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightNumber){
        return ResponseEntity.ok(flightService.deleteFlight(flightNumber));
    }
}
