package io.upschool.DTO;

import io.upschool.Entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouteSaveRequest {

	private Airport departureAirport;
	private Airport arrivalAirport;
}
