package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.flight.FlightDto;
import dev.xshoxruh.airline_reservation_system.entities.Flight;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightMapper {
    Flight toEntity(FlightDto flightDto);

    FlightDto toDto(Flight flight);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Flight partialUpdate(FlightDto flightDto, @MappingTarget Flight flight);
}