package io.upschool.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.upschool.DTO.RouteSaveRequest;
import io.upschool.DTO.RouteSaveResponse;
import io.upschool.DTO.RouteUpdateRequest;
import io.upschool.Entity.Airport;
import io.upschool.Entity.Route;
import io.upschool.Exceptions.AirportNotFoundException;
import io.upschool.Exceptions.RouteNotFoundException;
import io.upschool.Repository.AirportRepository;
import io.upschool.Repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class RouteService {
	
	private final RouteRepository routeRepository;
    private final AirportService airportService;
    private final AirportRepository airportRepository;

    public RouteSaveResponse save(RouteSaveRequest routeSaveRequest) {
    	
        Airport departureAirport = airportService.findAirportByCode(routeSaveRequest.getDepartureAirport().getAirportCode());
        if (departureAirport == null) {
            throw new AirportNotFoundException("Departure airport not found with code: " + routeSaveRequest.getDepartureAirport());
        }

        Airport arrivalAirport = airportService.findAirportByCode(routeSaveRequest.getArrivalAirport().getAirportCode());
        if (arrivalAirport == null) {
            throw new AirportNotFoundException("Arrival airport not found with code: " + routeSaveRequest.getArrivalAirport());
        }

        Route newRoute = Route.builder()
                              .departureAirport(departureAirport)
                              .arrivalAirport(arrivalAirport)
                              .build();

        Route savedRoute = routeRepository.save(newRoute);

        return RouteSaveResponse.builder()
                                .id(savedRoute.getRouteId())
                               .arrivalAirport(arrivalAirport)
                               .departureAirport(departureAirport)                          
                                .build();
    }   
    
    
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route findRouteById(Long id) {
        return routeRepository.findById(id).orElseThrow(()-> new RouteNotFoundException("Route not found with id: "+id));
    }
    
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElseThrow(()-> new RouteNotFoundException("Route not found with id: "+id));
    }
    
    public RouteSaveResponse update(RouteUpdateRequest routeUpdateRequest) {
       
        Route existingRoute = routeRepository.findById(routeUpdateRequest.getId())
                                             .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + routeUpdateRequest.getId()));

        
        Airport departureAirport = airportService.findAirportByCode(routeUpdateRequest.getDepartureAirport().getAirportCode());
        if (departureAirport == null) {
            throw new AirportNotFoundException("Departure airport not found with code: " + routeUpdateRequest.getDepartureAirport());
        }

        Airport arrivalAirport = airportService.findAirportByCode(routeUpdateRequest.getArrivalAirport().getAirportCode());
        if (arrivalAirport == null) {
            throw new AirportNotFoundException("Arrival airport not found with code: " + routeUpdateRequest.getArrivalAirport());
        }

        existingRoute.setDepartureAirport(departureAirport);
        existingRoute.setArrivalAirport(arrivalAirport);

        // Diğer alanları güncelle (isteğe bağlı)
        // existingRoute.setSomeOtherProperty(routeUpdateRequest.getSomeOtherProperty());

       
        Route updatedRoute = routeRepository.save(existingRoute);

        return RouteSaveResponse.builder()
                                .id(updatedRoute.getRouteId())
                                .departureAirport(departureAirport)
                                .arrivalAirport(arrivalAirport)
                                .build();
    }
    public void delete(Long id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            route.setRouteIsActive(false);
            routeRepository.save(route);
            routeRepository.deleteById(id);
        } else {
            throw new RouteNotFoundException("Route not found with id: " + id);
        }

}
   
    
   
}
