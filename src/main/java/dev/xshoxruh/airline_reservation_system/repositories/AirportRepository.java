package dev.xshoxruh.airline_reservation_system.repositories;

import dev.xshoxruh.airline_reservation_system.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Transactional
    @Modifying
    @Query("update Airport a set a.city = ?1, a.country = ?2 where upper(a.airportName) = upper(?3)")
    void updateCityAndCountryByAirportNameIgnoreCase(String city, String country, String airportName);

    @Transactional
    @Modifying
    @Query("delete from Airport a where upper(a.airportName) = upper(?1)")
    void deleteByAirportNameIgnoreCase(String airportName);

    @Query("select a from Airport a where upper(a.city) = upper(?1) order by a.id")
    List<Airport> findByCityIgnoreCaseOrderByIdAsc(String city);

    @Query("select a from Airport a where upper(a.airportName) = upper(?1)")
    Optional<Airport> findByAirportNameIgnoreCase(String airportName);
}