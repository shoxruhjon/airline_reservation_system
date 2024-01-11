package dev.xshoxruh.airline_reservation_system.services;

import dev.xshoxruh.airline_reservation_system.entities.Agent;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.entities.AuthUserOtp;
import org.springframework.lang.NonNull;

public interface AuthUserOtpService {

    AuthUserOtp create(@NonNull AuthUserOtp authUserOTP);

    AuthUserOtp createOTP(@NonNull AuthUser authUser);

    AuthUserOtp createOTPForAgent(@NonNull Agent agent);
}
