package io.upschool.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.DTO.AirlineSaveRequest;
import io.upschool.DTO.AirlineSaveResponse;
import io.upschool.DTO.AirlineUpdateRequest;
import io.upschool.Entity.Airline;
import io.upschool.Service.AirlineService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {
   
    private final AirlineService airlineService;

    @PostMapping
    public AirlineSaveResponse saveAirline(@RequestBody AirlineSaveRequest airlineSaveRequest) {
        return airlineService.save(airlineSaveRequest);
    }
    
  
    

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline findAirlineById(@PathVariable Long id) {
        return airlineService.findAirlineById(id);
    }

    @PutMapping("/{id}")
    public AirlineSaveResponse updateAirline(@PathVariable Long id, @RequestBody AirlineUpdateRequest airlineUpdateRequest) {
        airlineUpdateRequest.setId(id);
        return airlineService.update(airlineUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.delete(id);
    }
    
   
}
