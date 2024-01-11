package dev.xshoxruh.airline_reservation_system.controllers;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AuthUserCreateDto;
import dev.xshoxruh.airline_reservation_system.dtos.auth.Login;
import dev.xshoxruh.airline_reservation_system.dtos.auth.AgentDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import dev.xshoxruh.airline_reservation_system.entities.Reservation;
import dev.xshoxruh.airline_reservation_system.services.AgentService;
import dev.xshoxruh.airline_reservation_system.services.AuthUserService;
import dev.xshoxruh.airline_reservation_system.services.ReservationService;
import dev.xshoxruh.airline_reservation_system.services.imp.ReservationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;
    private final AgentService agentService;
    private final ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody AuthUserCreateDto dto){
        return ResponseEntity.ok(authUserService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody Login login){
        return ResponseEntity.ok(authUserService.login(login));
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateCode(@PathVariable String code){
        return ResponseEntity.ok(authUserService.activaceAccount(code));
    }

    @GetMapping("/agent/activate/{code}")
    public ResponseEntity<String> activateAgentCode(@PathVariable String code){
        return ResponseEntity.ok(agentService.activaceAccount(code));
    }

    @PostMapping("/agent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createAgent(@Valid @RequestBody AgentDto dto){
        return ResponseEntity.ok(agentService.createAgent(dto));
    }

    @GetMapping("/city")
    public ResponseEntity<List<Airport>> getCity(@RequestParam String city){
        return ResponseEntity.ok(authUserService.getCity(city));
    }

    @GetMapping("/airport")
    public ResponseEntity<Airport> getAirport(@RequestParam Long id){
        return ResponseEntity.ok(authUserService.getAirport(id));
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> reservation(@RequestParam Long flightNumber){
        return ResponseEntity.ok(reservationService.reserve(flightNumber));
    }

    @PostMapping("/cancel/reservation")
    public ResponseEntity<Reservation> cancelReservation(@RequestParam Long seatNumber){
        return ResponseEntity.ok(reservationService.reserve(seatNumber));
    }
}
