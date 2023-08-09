package io.upschool.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.upschool.DTO.AirportSaveRequest;
import io.upschool.DTO.AirportSaveResponse;
import io.upschool.DTO.AirportUpdateRequest;
import io.upschool.Entity.Airport;
import io.upschool.Exceptions.AirportNotFoundException;
import io.upschool.Repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {

	private final AirportRepository airportRepository;
	
	public AirportSaveResponse save(AirportSaveRequest airportSaveRequest) {
		var newAirport=Airport.builder()
				.airportName(airportSaveRequest.getAirportName())
				.airportCode(airportSaveRequest.getAirportCode())
				.build();
		
		Airport savedAirport=airportRepository.save(newAirport);
		
		return AirportSaveResponse
				.builder()
				.id(savedAirport.getId())
				.airportCode(savedAirport.getAirportCode())
				.airportName(savedAirport.getAirportName())
				.build();
	}

	public List<Airport>getAllAirports(){
		return airportRepository.findAll();
	}
	
	public Airport findAirportById(Long id) {
		return airportRepository.findById(id).orElseThrow(()-> new AirportNotFoundException("Airport not found with id: "+ id));
	}
   
	public AirportSaveResponse update(AirportUpdateRequest request) {
		var optionalAirport=airportRepository.findById(request.getId());
		if(optionalAirport.isPresent()) {
			var airport =optionalAirport.get();
			airport.setAirportName(request.getAirportName());
			airport.setAirportCode(request.getAirportCode());
			airport=airportRepository.save(airport);
			return AirportSaveResponse
			.builder()
			.airportName(airport.getAirportName())
			.id(airport.getId())
			.build();
		}
		throw new AirportNotFoundException("Airport is not found"+ request.getId());
	}
	
	public void delete(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            airport.setAirportIsActive(false);
            airportRepository.save(airport);
            airportRepository.deleteById(id);
        } else {
            throw new AirportNotFoundException("Airport not found with id: " + id);
        }
    }
	
	public Airport findAirportByCode(String airportCode) {
       
        return airportRepository.findByAirportCode(airportCode);
    }
	
}