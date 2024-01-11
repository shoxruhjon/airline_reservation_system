package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AgentDto;
import dev.xshoxruh.airline_reservation_system.entities.Agent;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-07T14:06:03+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class AgentMapperImpl implements AgentMapper {

    @Override
    public Agent toEntity(AgentDto agentCreateDto) {
        if ( agentCreateDto == null ) {
            return null;
        }

        Agent.AgentBuilder agent = Agent.builder();

        agent.username( agentCreateDto.getUsername() );
        agent.email( agentCreateDto.getEmail() );
        agent.password( agentCreateDto.getPassword() );
        agent.agentName( agentCreateDto.getAgentName() );

        return agent.build();
    }

    @Override
    public AgentDto toDto(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentDto agentDto = new AgentDto();

        agentDto.setUsername( agent.getUsername() );
        agentDto.setEmail( agent.getEmail() );
        agentDto.setPassword( agent.getPassword() );
        agentDto.setAgentName( agent.getAgentName() );

        return agentDto;
    }

    @Override
    public Agent partialUpdate(AgentDto agentCreateDto, Agent agent) {
        if ( agentCreateDto == null ) {
            return agent;
        }

        if ( agentCreateDto.getUsername() != null ) {
            agent.setUsername( agentCreateDto.getUsername() );
        }
        if ( agentCreateDto.getEmail() != null ) {
            agent.setEmail( agentCreateDto.getEmail() );
        }
        if ( agentCreateDto.getPassword() != null ) {
            agent.setPassword( agentCreateDto.getPassword() );
        }
        if ( agentCreateDto.getAgentName() != null ) {
            agent.setAgentName( agentCreateDto.getAgentName() );
        }

        return agent;
    }
}
