package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.config.security.SessionUser;
import dev.xshoxruh.airline_reservation_system.dtos.auth.AgentDto;
import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AgentOtp;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.mappers.AgentMapper;
import dev.xshoxruh.airline_reservation_system.repositories.AgentOtpRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AgentRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserRepository;
import dev.xshoxruh.airline_reservation_system.services.AgentOtpService;
import dev.xshoxruh.airline_reservation_system.services.AgentService;
import dev.xshoxruh.airline_reservation_system.utils.MailSenderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;
    private final AuthUserOtpServiceImpl authUserOtpService;
    private final MailSenderService mailSenderService;
    private final AgentOtpRepository agentOtpRepository;
    private final AgentOtpService agentOtpService;
    private final SessionUser sessionUser;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createAgent(@NonNull AgentDto dto) {
        Agent agent = agentMapper.toEntity(dto);
        if (agentRepository.findAgentByEmail(dto.getEmail()).isPresent())
            throw new RuntimeException("Email Already Taken");
        if (agentRepository.findAgentByUsername(dto.getUsername()).isPresent())
            throw new RuntimeException("Username Already Taken");
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new RuntimeException("Passwords must be the same");
        agent.setRole("AGENT");
        agent.setCreatedAt(LocalDateTime.now());
        Long id = sessionUser.id();
        agent.setCreatedBy(id);
        agentRepository.save(agent);
        AuthUser user = new AuthUser();
        user.setUsername(agent.getUsername());
        user.setPassword(passwordEncoder.encode(agent.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail(agent.getEmail());
        user.setRole("AGENT");
        authUserRepository.save(user);
        AgentOtp otp = agentOtpService.createOTPForAgent(agent);
        Map<String, String> model = new HashMap<>();
        model.put("to", agent.getEmail());
        model.put("code", otp.getCode());
        model.put("role", "AGENT");
        mailSenderService.sendActivationMail(model);
        return "Success";
    }

    @Override
    public String activaceAccount(@NonNull String code) {
        AgentOtp otp = agentOtpRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RuntimeException("Invalid Code"));
        if (otp.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Code is expired");

        Long userID = otp.getUserID();
        agentRepository.activateUser(userID);
        agentOtpRepository.deleteById(userID);
        return "Account Successfully Activated";
    }
}
