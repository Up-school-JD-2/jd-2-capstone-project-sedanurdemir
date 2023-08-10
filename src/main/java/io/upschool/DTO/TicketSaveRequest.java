package io.upschool.DTO;

import io.micrometer.common.lang.Nullable;
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
public class TicketSaveRequest {

	private String ticketNumber;
	private Long flightId;

	
	private String passengerFirstName;
	private String passengerLastName;
	
	@Nullable
	private String CreditCard;

	 private boolean isCancelled;
}
