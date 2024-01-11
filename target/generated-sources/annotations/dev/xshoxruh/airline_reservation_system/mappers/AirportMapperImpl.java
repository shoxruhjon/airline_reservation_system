package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.AirportDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-07T16:10:10+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class AirportMapperImpl implements AirportMapper {

    @Override
    public Airport toEntity(AirportDto airportDto) {
        if ( airportDto == null ) {
            return null;
        }

        Airport.AirportBuilder airport = Airport.builder();

        airport.airportName( airportDto.getAirportName() );
        airport.city( airportDto.getCity() );
        airport.country( airportDto.getCountry() );

        return airport.build();
    }

    @Override
    public AirportDto toDto(Airport airport) {
        if ( airport == null ) {
            return null;
        }

        AirportDto airportDto = new AirportDto();

        airportDto.setAirportName( airport.getAirportName() );
        airportDto.setCity( airport.getCity() );
        airportDto.setCountry( airport.getCountry() );

        return airportDto;
    }

    @Override
    public Airport partialUpdate(AirportDto airportDto, Airport airport) {
        if ( airportDto == null ) {
            return airport;
        }

        if ( airportDto.getAirportName() != null ) {
            airport.setAirportName( airportDto.getAirportName() );
        }
        if ( airportDto.getCity() != null ) {
            airport.setCity( airportDto.getCity() );
        }
        if ( airportDto.getCountry() != null ) {
            airport.setCountry( airportDto.getCountry() );
        }

        return airport;
    }
}
