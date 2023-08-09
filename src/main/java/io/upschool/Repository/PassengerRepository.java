package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.upschool.Entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
