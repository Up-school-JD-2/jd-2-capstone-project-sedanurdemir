package io.upschool.DTO;



import io.upschool.Entity.Airline;
import io.upschool.Entity.Airport;
import io.upschool.Entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveResponse {
	
	 private Long id;
	    private String flightNumber;
	    private int totalSeats;
	    private Route route;
	    private int soldedSeats;
	   
	    private Airline airline;
	    
	    private Airport departureCity;
	    private Airport arrivalCity;
}

