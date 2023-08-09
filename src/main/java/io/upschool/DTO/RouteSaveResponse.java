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
@Builder
@Getter
@Setter
public class RouteSaveResponse {

	private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
}