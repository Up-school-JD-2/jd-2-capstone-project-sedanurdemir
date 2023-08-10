package io.upschool.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	
	 List<Flight> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);

}
