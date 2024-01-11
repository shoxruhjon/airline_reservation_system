package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.AgentOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentOtpRepository extends JpaRepository<AgentOtp, Long> {

    @Query("select a from AgentOtp a where upper(a.code) = upper(?1)")
    Optional<AgentOtp> findByCodeIgnoreCase(String code);
}