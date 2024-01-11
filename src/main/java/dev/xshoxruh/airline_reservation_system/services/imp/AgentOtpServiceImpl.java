package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AgentOtp;
import dev.xshoxruh.airline_reservation_system.repositories.AgentOtpRepository;
import dev.xshoxruh.airline_reservation_system.services.AgentOtpService;
import dev.xshoxruh.airline_reservation_system.utils.BaseUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgentOtpServiceImpl implements AgentOtpService {
    private final AgentOtpRepository repository;
    private final BaseUtils utils;

    @Override
    public AgentOtp create(@NonNull AgentOtp agentOtp) {
        return repository.save(agentOtp);
    }

    @Override
    public AgentOtp createOTPForAgent(@NonNull Agent agent) {
        AgentOtp agentOtp = AgentOtp.builder()
                .code(utils.generateOtp(agent.getId()))
                .userID(agent.getId())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        return create(agentOtp);
    }
}
