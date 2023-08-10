package io.upschool.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import io.upschool.DTO.TicketSaveResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number", length = 50, unique = true)
    private String ticketNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "credit_card", length = 20)
    private String creditCard;

    @Column(name = "is_cancelled", nullable = false)
    private boolean isCancelled;
    
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;
    
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    private LocalDateTime cancellationDate;
	

    public void generateTicketNumber() {
       
    	 String generatedTicketNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
         this.ticketNumber = generatedTicketNumber;
    }


	

	public TicketSaveResponse toTicketSaveResponse() {
		
		return TicketSaveResponse.builder()
                .ticketId(this.id)
                .ticketNumber(this.ticketNumber)
                .passengerFirtsName(this.getPassenger().getFirstName())
                .passengerLastName(this.getPassenger().getLastName())
                .flightId(this.flight.getId())
                .CreditCard(this.creditCard)
                .isCancelled(this.isCancelled)
                .build();
	}
   
}

