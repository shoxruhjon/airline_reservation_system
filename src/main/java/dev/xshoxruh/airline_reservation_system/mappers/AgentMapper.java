package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.dtos.auth.AgentDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AgentMapper {

    Agent toEntity(AgentDto agentCreateDto);

    AgentDto toDto(Agent agent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Agent partialUpdate(AgentDto agentCreateDto, @MappingTarget Agent agent);
}