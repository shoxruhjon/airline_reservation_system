package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.repositories.AgentRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserRepository;
import dev.xshoxruh.airline_reservation_system.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AgentRepository agentRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public String blockAgent(@NonNull String username) {
        if (!agentRepository.findAgentByUsername(username).isPresent())
            throw new RuntimeException("Not found");
        agentRepository.updateActiveByUsernameIgnoreCase(false, username);
        return "Blocked";
    }

    @Override
    public String activateAgent(@NonNull String username) {
        if (!agentRepository.findAgentByUsername(username).isPresent())
            throw new RuntimeException("Not found");
        agentRepository.updateActiveByUsernameIgnoreCase(true, username);
        return "Activated";
    }

    @Override
    public String blockUser(@NonNull String username) {
        if (!authUserRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Not found");
        authUserRepository.updateActiveByUsernameIgnoreCase(false, username);
        return "Blocked";
    }

    @Override
    public String activateUser(@NonNull String username) {
        if (!authUserRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Not found");
        authUserRepository.updateActiveByUsernameIgnoreCase(true, username);
        return "Activated";
    }
}
