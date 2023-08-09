package io.upschool.DTO;

import io.upschool.Entity.Airline;
import io.upschool.Entity.Airport;
import io.upschool.Entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FlightSaveRequest {

	 	private String flightNumber;
	    private int totalSeats;
	    private Route route;
	    private Airline airline;
	   
	    private Airport departureCity;
	    private Airport arrivalCity;
	   
}

