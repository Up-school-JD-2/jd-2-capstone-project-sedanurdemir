package io.upschool.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.DTO.AirportSaveRequest;
import io.upschool.DTO.AirportSaveResponse;
import io.upschool.DTO.AirportUpdateRequest;
import io.upschool.Entity.Airport;
import io.upschool.Service.AirportService;
import io.upschool.Service.FlightService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor

public class AirportController {

	private final AirportService airportService;
	private final FlightService flightService;
	
	@GetMapping()
	public ResponseEntity<List<Airport>> getAirports(){
		var airports =airportService.getAllAirports();
		return ResponseEntity.ok(airports);
	}
	
	@PostMapping
	public ResponseEntity<AirportSaveResponse> createAirport(@RequestBody AirportSaveRequest airportSaveRequest){
		var response=airportService.save(airportSaveRequest);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
    public AirportSaveResponse updateAirport(@PathVariable Long id, @RequestBody AirportUpdateRequest airportUpdateRequest) {
        airportUpdateRequest.setId(id);
        return airportService.update(airportUpdateRequest);
    }
	
	@DeleteMapping("/{id}")
    public void deleteAirports(@PathVariable Long id) {
        airportService.delete(id);
    }
}