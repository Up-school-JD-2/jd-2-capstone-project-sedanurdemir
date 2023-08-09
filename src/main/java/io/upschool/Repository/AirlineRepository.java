package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>{

//	Airline findByAirlineId(Long id);
}
