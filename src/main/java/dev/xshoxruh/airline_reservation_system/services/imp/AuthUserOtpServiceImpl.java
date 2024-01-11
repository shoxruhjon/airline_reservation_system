package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.entities.AuthUserOtp;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserOTPRepository;
import dev.xshoxruh.airline_reservation_system.services.AuthUserOtpService;
import dev.xshoxruh.airline_reservation_system.utils.BaseUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthUserOtpServiceImpl implements AuthUserOtpService {
    private final AuthUserOTPRepository repository;
    private final BaseUtils utils;

    @Override
    public AuthUserOtp create(@NonNull AuthUserOtp authUserOTP) {
        return repository.save(authUserOTP);
    }

    @Override
    public AuthUserOtp createOTP(@NonNull AuthUser authUser) {
        AuthUserOtp authUserOtp = AuthUserOtp.builder()
                .code(utils.generateOtp(authUser.getId()))
                .userID(authUser.getId())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        return create(authUserOtp);
    }

    @Override
    public AuthUserOtp createOTPForAgent(@NonNull Agent agent) {
        AuthUserOtp authUserOtp = AuthUserOtp.builder()
                .code(utils.generateOtp(agent.getId()))
                .userID(agent.getId())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        return create(authUserOtp);
    }
}
