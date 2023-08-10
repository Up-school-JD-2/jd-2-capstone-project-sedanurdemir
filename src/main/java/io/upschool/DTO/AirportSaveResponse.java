package io.upschool.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSaveResponse {

	private Long id;
	private String airportCode;
	private String airportName;
	private String cityName;
}
