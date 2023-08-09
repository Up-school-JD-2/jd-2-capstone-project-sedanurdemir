package io.upschool.DTO;

import io.upschool.Entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteUpdateRequest {

	private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
}
