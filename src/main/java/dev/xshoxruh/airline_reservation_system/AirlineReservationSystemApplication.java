package dev.xshoxruh.airline_reservation_system;

import dev.xshoxruh.airline_reservation_system.config.security.SessionUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableAsync
public class AirlineReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return (args -> {
		});
	}

	@Bean
	public AuditorAware<Long> getAuditor(SessionUser sessionUser) {
		return () -> Optional.of(sessionUser.id());
	}
}
