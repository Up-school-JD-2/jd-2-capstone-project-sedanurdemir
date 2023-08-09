package io.upschool.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import io.upschool.DTO.FlightSaveRequest;
import io.upschool.DTO.FlightSaveResponse;
import io.upschool.DTO.FlightUpdateRequest;
import io.upschool.Entity.Airline;
import io.upschool.Entity.Flight;
import io.upschool.Entity.Route;
import io.upschool.Exceptions.AirlineNotFoundException;
import io.upschool.Exceptions.FlightNotFoundException;
import io.upschool.Exceptions.RouteNotFoundException;
import io.upschool.Repository.FlightRepository;
import io.upschool.Repository.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

	private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final RouteService routeService;
    private final TicketRepository ticketRepository;

    public Flight save(Flight flight) {
    	var airline =airlineService.save(flight.getAirline());
    	flight.setAirline(airline);
    	return flightRepository.save(flight);
    
    }

    
    @Transactional
    public FlightSaveResponse save(FlightSaveRequest flightSaveRequest) {
        Route route = routeService.findRouteById(flightSaveRequest.getRoute().getRouteId());
        
        if (route == null) {
        	throw new RouteNotFoundException("Flight not found with id: "+ flightSaveRequest.getRoute());
        }
       
        Airline airline=airlineService.findAirlineById(flightSaveRequest.getAirline().getId());
       
        
         if (airline == null) {
        	throw new AirlineNotFoundException("Airline not found with id: "+ flightSaveRequest.getAirline());
        }

        
		Flight newFlight = Flight.builder()
                                 .route(route)
                                 .airline(airline)
                                 .flightNumber(flightSaveRequest.getFlightNumber())
                                 .departureCity(route.getDepartureAirport().getAirportName())
                                 .arrivalCity(route.getArrivalAirport().getAirportName())
                                 .totalSeats(flightSaveRequest.getTotalSeats())
                                 .soldSeats(0) 
                                 .build();

        Flight savedFlight = flightRepository.save(newFlight);

        return FlightSaveResponse.builder()
                                 .id(savedFlight.getId())
                                 .flightNumber(savedFlight.getFlightNumber())
                                .arrivalCity(savedFlight.getRoute().getArrivalAirport())
                                .departureCity(savedFlight.getRoute().getArrivalAirport())
                                 .totalSeats(savedFlight.getTotalSeats())
                                 .soldedSeats(savedFlight.getSoldSeats())
                                 .airline(savedFlight.getAirline())
                                 .route(savedFlight.getRoute())
                                 .build();
    }
//    

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public FlightSaveResponse update(FlightUpdateRequest flightUpdateRequest) {
        Long flightId = flightUpdateRequest.getId();
        Flight existingFlight = flightRepository.findById(flightId)
                    .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + flightId));

        Route route = routeService.findRouteById(flightUpdateRequest.getRoute().getRouteId());
        if (route == null) {
            throw new RouteNotFoundException("Route not found with id: " + flightUpdateRequest.getRoute());
        }

        existingFlight.setRoute(route);
        existingFlight.setFlightNumber(flightUpdateRequest.getFlightNumber());
        existingFlight.setTotalSeats(flightUpdateRequest.getTotalSeats());
        existingFlight.setDepartureCity(flightUpdateRequest.getDepartureCity().getAirportName());
        existingFlight.setArrivalCity(flightUpdateRequest.getArrivalCity().getAirportName());
        

        Flight updatedFlight = flightRepository.save(existingFlight);

        return FlightSaveResponse.builder()
                                 .id(updatedFlight.getId())
                                 .flightNumber(updatedFlight.getFlightNumber())
                                 .departureCity(updatedFlight.getRoute().getDepartureAirport())
                                 .arrivalCity(updatedFlight.getRoute().getArrivalAirport())
                                 .totalSeats(updatedFlight.getTotalSeats())
                                 .soldedSeats(updatedFlight.getSoldSeats())
                                 .route(updatedFlight.getRoute())
                                 .build();
    }
    
    
    
    
    public void delete(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            flight.setFlightIsActive(false);
            flightRepository.save(flight);
            flightRepository.deleteById(id);
        } else {
        	throw new FlightNotFoundException("Flight not found with id: " + id);

        }
    }
    public List<Flight> findFlights(String departureCity, String arrivalCity) {
        
        return flightRepository.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
    }

}
