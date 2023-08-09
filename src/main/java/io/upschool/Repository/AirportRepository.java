package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.upschool.Entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
