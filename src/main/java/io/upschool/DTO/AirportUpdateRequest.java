package io.upschool.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportUpdateRequest {

	private Long id;
	private String airportCode;
	private String airportName;
	private String cityName;
}
