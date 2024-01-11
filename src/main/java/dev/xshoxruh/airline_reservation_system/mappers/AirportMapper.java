package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.AirportDto;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirportMapper {
    Airport toEntity(AirportDto airportDto);

    AirportDto toDto(Airport airport);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Airport partialUpdate(AirportDto airportDto, @MappingTarget Airport airport);
}