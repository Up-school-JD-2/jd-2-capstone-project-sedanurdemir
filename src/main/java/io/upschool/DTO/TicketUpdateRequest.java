package io.upschool.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketUpdateRequest {

	private Long id;
	private String ticketNumber;
	private Long flightId;
	private String maskedCreditCard;
	private String passengerName;
}
