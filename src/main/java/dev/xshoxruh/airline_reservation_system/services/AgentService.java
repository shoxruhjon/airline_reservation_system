package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AgentDto;
import org.springframework.lang.NonNull;

public interface AgentService {

    String createAgent(@NonNull AgentDto dto);

    String activaceAccount(@NonNull String code);
}
