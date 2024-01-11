package dev.xshoxruh.airline_reservation_system.services;

import org.springframework.lang.NonNull;

public interface AdminService {

    String blockAgent(@NonNull String username);

    String activateAgent(@NonNull String username);

    String blockUser(@NonNull String username);

    String activateUser(@NonNull String username);
}
