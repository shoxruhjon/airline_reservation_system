package dev.xshoxruh.airline_reservation_system.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Getter
public class BaseUtils {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
    private final Base64.Encoder encoder = Base64.getUrlEncoder();

    public String generateOtp(Long userID) {
        return encoder.encodeToString((UUID.randomUUID().toString() + userID).getBytes());
    }

    public String format(LocalDateTime localDateTime) {
        return FORMATTER.format(localDateTime);
    }

}
