package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    @Query("select a from AuthUser a where upper(a.username) = upper(?1) and a.deleted = false")
    Optional<AuthUser> findByUsername(String username);
    @Query("select a from AuthUser a where upper(a.email) = upper(?1)")
    Optional<AuthUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.active = true where a.id = ?1 and a.deleted = false")
    void activateUser(Long userID);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.active = ?1 where upper(a.username) = upper(?2)")
    void updateActiveByUsernameIgnoreCase(boolean active, String username);
}