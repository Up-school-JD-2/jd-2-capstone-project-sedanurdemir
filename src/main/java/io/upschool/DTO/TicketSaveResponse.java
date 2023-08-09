package io.upschool.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {

	private Long ticketId;
	private String ticketNumber;
	private Long flightId;
	private String CreditCard;
	private boolean isCancelled;
	 private String passengerFirtsName;
	 private String passengerLastName;
	 private Long passengerId;
	 private LocalDateTime cancellationDate;
	 private LocalDateTime saleDate;
	
}
