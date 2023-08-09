package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.upschool.Entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long>{

}
