package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.dtos.AirportDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import dev.xshoxruh.airline_reservation_system.mappers.AirportMapper;
import dev.xshoxruh.airline_reservation_system.repositories.AirportRepository;
import dev.xshoxruh.airline_reservation_system.services.AirportService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportMapper airportMapper;
    private final AirportRepository airportRepository;

    @Override
    public String createAirport(@NonNull AirportDto dto) {
        Airport airport = airportMapper.toEntity(dto);
        airportRepository.save(airport);
        return "Created Successfully";
    }

    @Override
    public String updateAirport(@NonNull AirportDto dto) {
        Airport airport = airportMapper.toEntity(dto);
        airportRepository.updateCityAndCountryByAirportNameIgnoreCase(airport.getCity(), airport.getCountry(), airport.getAirportName());
        return null;
    }

    @Override
    public String deleteAirport(@NonNull Long id) {
        if (airportRepository.findById(id).isEmpty())
            throw new RuntimeException("Not Found");
        airportRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getById(@NonNull Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
