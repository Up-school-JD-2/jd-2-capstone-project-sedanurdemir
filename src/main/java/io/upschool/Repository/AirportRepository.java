package io.upschool.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	Airport findByAirportCode(String airportCode);

}
