package dev.xshoxruh.airline_reservation_system.services.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.xshoxruh.airline_reservation_system.config.security.SessionUser;
import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import dev.xshoxruh.airline_reservation_system.mappers.FlightMapper;
import dev.xshoxruh.airline_reservation_system.repositories.AgentRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserRepository;
import dev.xshoxruh.airline_reservation_system.repositories.FlightRepository;
import dev.xshoxruh.airline_reservation_system.services.FlightService;
import dev.xshoxruh.airline_reservation_system.utils.BaseUtils;
import dev.xshoxruh.airline_reservation_system.utils.MailSenderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;
    private final SessionUser sessionUser;
    private final AgentRepository agentRepository;
    private final BaseUtils baseUtils;
    private final AuthUserRepository authUserRepository;
    private final MailSenderService mailSenderService;

    @Override
    public String createFlights(@NonNull MultipartFile flights) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Long id = sessionUser.id();
        try {
            byte[] bytes = flights.getBytes();
            String jsonData = new String(bytes);
            System.out.println(jsonData);
            List<Flight> flightList = mapper.readValue(jsonData, new TypeReference<>() {
            });
            for (Flight flight : flightList) {
                flight.setCreatedBy(id);
                flight.setCreatedAt(LocalDateTime.now());
            }
            flightRepository.saveAll(flightList);
            List<AuthUser> users = authUserRepository.findAll();
            for (AuthUser user : users) {
                Map<String, String> model = new HashMap<>();
                model.put("to", user.getEmail());
                model.put("username", user.getUsername());
                mailSenderService.sendNewFlightMail(model);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Created Successfully.";
    }

    @Override
    public String updateFlight(@NonNull FlightDto dto) {
        Flight flight = flightMapper.toEntity(dto);
        flight.setUpdatedBy(sessionUser.id());
        flightRepository.update(
                flight.getDepartureFrom(),
                flight.getArrivalTo(),
                flight.getDepartureDateTime(),
                flight.getArrivalDateTime(),
                flight.getAvailableSeats(),
                flight.getTicketPrice(),
                flight.getUpdatedBy(),
                flight.getFlightNumber()
        );
        List<AuthUser> users = authUserRepository.findAll();
        for (AuthUser user : users) {
            Map<String, String> model = new HashMap<>();
            model.put("to", user.getEmail());
            model.put("username", user.getUsername());
            mailSenderService.sendUpdatedFlightMail(model);
        }
        return "Updated Successfully.";
    }

    @Override
    public String deleteFlight(@NonNull Long flightNumber) {
        if(flightRepository.findByFlightNumber(flightNumber).isEmpty())
            throw new RuntimeException("Flight is not found");
        flightRepository.deleteByFlightNumber(flightNumber);
        return "Deleted";
    }

    @Override
    public Flight getFlight(@NonNull Long flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight is not found"));
    }

    @Override
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

}
