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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.DTO.FlightSaveRequest;
import io.upschool.DTO.FlightSaveResponse;
import io.upschool.DTO.FlightUpdateRequest;
import io.upschool.Entity.Flight;
import io.upschool.Service.AirlineService;
import io.upschool.Service.AirportService;
import io.upschool.Service.FlightService;
import io.upschool.Service.RouteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

	private final FlightService flightService;
	
	private RouteService routeService;

    
    private AirlineService airlineService;

    
    private AirportService airportService;
	@PostMapping
    public FlightSaveResponse saveFlight(@RequestBody FlightSaveRequest flightSaveRequest) {
        return flightService.save(flightSaveRequest);
    }

//	
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight findFlightById(@PathVariable Long id) {
        return flightService.findFlightById(id);
    }

    @PutMapping("/{id}")
    public FlightSaveResponse updateFlight(@PathVariable Long id, @RequestBody FlightUpdateRequest flightUpdateRequest) {
        flightUpdateRequest.setId(id);
        return flightService.update(flightUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.delete(id);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam(name = "departureCity") String departureCity,
            @RequestParam(name = "arrivalCity") String arrivalCity) {
        List<Flight> flights = flightService.findFlights(departureCity, arrivalCity);
        return ResponseEntity.ok(flights);
    }
    
}

