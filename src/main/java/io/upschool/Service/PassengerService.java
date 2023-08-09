package io.upschool.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.upschool.Entity.Passenger;
import io.upschool.Exceptions.AirlineNotFoundException;
import io.upschool.Repository.PassengerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerService {

	private final PassengerRepository passengerRepository;

	public void delete(Long id) {
	    Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
	    if (optionalPassenger.isPresent()) {
	        Passenger passenger = optionalPassenger.get();
	      
	        passengerRepository.save(passenger);
	        passengerRepository.deleteById(id);
	    } else {
	        throw new AirlineNotFoundException("Airline not found with id: " + id);
	    }
	}
}
