package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.AuthUserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthUserOTPRepository extends JpaRepository<AuthUserOtp, Long> {
    @Query("select a from AuthUserOtp a where upper(a.code) = upper(?1)")
    Optional<AuthUserOtp> findByCodeIgnoreCase(String code);
}