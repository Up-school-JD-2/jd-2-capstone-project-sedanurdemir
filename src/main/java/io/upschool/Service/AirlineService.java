package io.upschool.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.upschool.DTO.AirlineSaveRequest;
import io.upschool.DTO.AirlineSaveResponse;
import io.upschool.DTO.AirlineUpdateRequest;
import io.upschool.Entity.Airline;
import io.upschool.Entity.Airport;
import io.upschool.Exceptions.AirlineNotFoundException;
import io.upschool.Repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
@Getter
public class AirlineService {
	
	private final AirlineRepository airlineRepository;
	
	public AirlineSaveResponse save(AirlineSaveRequest airlineSaveRequest) {
    Airline newAirline = Airline.builder()
                                .airlineCode(airlineSaveRequest.getAirlineCode())
                                .airlineName(airlineSaveRequest.getAirlineName())
                                .build();

    Airline savedAirline = airlineRepository.save(newAirline);

    return AirlineSaveResponse.builder()
                              .id(savedAirline.getId())
                              .airlineCode(savedAirline.getAirlineCode())
                              .airlineName(savedAirline.getAirlineName())
                              .build();
}
	
	public Airline save(Airline airline) {
		return airlineRepository.save(airline);
	}

	public List<Airline> getAllAirlines() {
    return airlineRepository.findAll();
}

	public Airline findAirlineById(Long id) {
     return airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException("Airline not found with id: " + id));
}

	public AirlineSaveResponse update(AirlineUpdateRequest request) {
    Optional<Airline> optionalAirline = airlineRepository.findById(request.getId());
    if (optionalAirline.isPresent()) {
        Airline airline = optionalAirline.get();
        airline.setAirlineCode(request.getAirlineCode());
        airline.setAirlineName(request.getAirlineName());
        airline = airlineRepository.save(airline);

        return AirlineSaveResponse.builder()
                                  .id(airline.getId())
                                  .airlineCode(airline.getAirlineCode())
                                  .airlineName(airline.getAirlineName())
                                  .build();
    }
    throw new AirlineNotFoundException("Airline not found with id: " + request.getId());
}

public void delete(Long id) {
    Optional<Airline> optionalAirline = airlineRepository.findById(id);
    if (optionalAirline.isPresent()) {
        Airline airline = optionalAirline.get();
        airline.setAirlineIsActive(false);
        airlineRepository.save(airline);
        airlineRepository.deleteById(id);
    } else {
        throw new AirlineNotFoundException("Airline not found with id: " + id);
    }
}

	

}
