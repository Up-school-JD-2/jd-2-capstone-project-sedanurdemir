package io.upschool.DTO;

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
public class FlightUpdateRequest {
	private Long id;
    private String flightNumber;
    private int totalSeats;
    private Route route;
    private Airport departureCity;
    private Airport arrivalCity;
   
}
