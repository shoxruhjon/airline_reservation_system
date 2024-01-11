package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-07T14:06:03+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Flight toEntity(FlightDto flightDto) {
        if ( flightDto == null ) {
            return null;
        }

        Flight.FlightBuilder flight = Flight.builder();

        flight.flightNumber( flightDto.getFlightNumber() );
        flight.departureFrom( flightDto.getDepartureFrom() );
        flight.arrivalTo( flightDto.getArrivalTo() );
        flight.departureDateTime( flightDto.getDepartureDateTime() );
        flight.arrivalDateTime( flightDto.getArrivalDateTime() );
        flight.availableSeats( flightDto.getAvailableSeats() );
        flight.ticketPrice( flightDto.getTicketPrice() );

        return flight.build();
    }

    @Override
    public FlightDto toDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightDto flightDto = new FlightDto();

        flightDto.setFlightNumber( flight.getFlightNumber() );
        flightDto.setDepartureFrom( flight.getDepartureFrom() );
        flightDto.setArrivalTo( flight.getArrivalTo() );
        flightDto.setDepartureDateTime( flight.getDepartureDateTime() );
        flightDto.setArrivalDateTime( flight.getArrivalDateTime() );
        flightDto.setAvailableSeats( flight.getAvailableSeats() );
        flightDto.setTicketPrice( flight.getTicketPrice() );

        return flightDto;
    }

    @Override
    public Flight partialUpdate(FlightDto flightDto, Flight flight) {
        if ( flightDto == null ) {
            return flight;
        }

        if ( flightDto.getFlightNumber() != null ) {
            flight.setFlightNumber( flightDto.getFlightNumber() );
        }
        if ( flightDto.getDepartureFrom() != null ) {
            flight.setDepartureFrom( flightDto.getDepartureFrom() );
        }
        if ( flightDto.getArrivalTo() != null ) {
            flight.setArrivalTo( flightDto.getArrivalTo() );
        }
        if ( flightDto.getDepartureDateTime() != null ) {
            flight.setDepartureDateTime( flightDto.getDepartureDateTime() );
        }
        if ( flightDto.getArrivalDateTime() != null ) {
            flight.setArrivalDateTime( flightDto.getArrivalDateTime() );
        }
        if ( flightDto.getAvailableSeats() != null ) {
            flight.setAvailableSeats( flightDto.getAvailableSeats() );
        }
        if ( flightDto.getTicketPrice() != null ) {
            flight.setTicketPrice( flightDto.getTicketPrice() );
        }

        return flight;
    }
}
