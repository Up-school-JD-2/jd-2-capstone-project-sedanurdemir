package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	Passenger findPassengerByLastName(String lastName);
}

