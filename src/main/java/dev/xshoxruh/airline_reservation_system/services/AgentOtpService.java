package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AgentOtp;
import org.springframework.lang.NonNull;

public interface AgentOtpService {

    AgentOtp create(@NonNull AgentOtp authUserOTP);

    AgentOtp createOTPForAgent(@NonNull Agent agent);
}
