package dev.xshoxruh.airline_reservation_system.controllers;

import dev.xshoxruh.airline_reservation_system.dtos.AirportDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import dev.xshoxruh.airline_reservation_system.services.AdminService;
import dev.xshoxruh.airline_reservation_system.services.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final AirportService airportService;

    public AdminController(AdminService adminService, AirportService airportService) {
        this.adminService = adminService;
        this.airportService = airportService;
    }

    @PutMapping("/block/agent/{username}")
    public ResponseEntity<String> blockAgent(@PathVariable String username){
        return ResponseEntity.ok(adminService.blockAgent(username));
    }

    @PutMapping("/activate/agent/{username}")
    public ResponseEntity<String> activateAgent(@PathVariable String username){
        return ResponseEntity.ok(adminService.activateAgent(username));
    }

    @PutMapping("/block/user/{username}")
    public ResponseEntity<String> blockUser(@PathVariable String username){
        return ResponseEntity.ok(adminService.blockUser(username));
    }

    @PutMapping("/activate/user/{username}")
    public ResponseEntity<String> activateUser(@PathVariable String username){
        return ResponseEntity.ok(adminService.activateAgent(username));
    }

    @PostMapping("/airport")
    public ResponseEntity<String> createAirport(@RequestBody AirportDto dto){
        return ResponseEntity.ok(airportService.createAirport(dto));
    }

    @PutMapping("/airport")
    public ResponseEntity<String> updateAirport(@RequestBody AirportDto dto){
        return ResponseEntity.ok(airportService.updateAirport(dto));
    }

    @DeleteMapping("/airport/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id){
        return ResponseEntity.ok(airportService.deleteAirport(id));
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getById(id));
    }

}
