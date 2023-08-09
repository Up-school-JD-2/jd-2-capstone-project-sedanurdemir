package io.upschool.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.DTO.TicketSaveRequest;
import io.upschool.DTO.TicketSaveResponse;
import io.upschool.Entity.Ticket;
import io.upschool.Service.FlightService;
import io.upschool.Service.TicketService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;
	private final FlightService flightService;
	@PostMapping
    public TicketSaveResponse saveTicket(@RequestBody TicketSaveRequest ticketSaveRequest) {
        return ticketService.save(ticketSaveRequest);
    }

    @GetMapping
    public List<TicketSaveResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket findTicketById(@PathVariable Long id) {
        return ticketService.findTicketById(id);
    }

//    @PutMapping("/{ticketId}")
//    public ResponseEntity<TicketUpdateRequest> updateTicket(@PathVariable Long ticketId, @RequestBody TicketUpdateRequest ticketUpdateRequest) {
//        TicketUpdateRequest response = ticketService.updateTicket(ticketId, ticketUpdateRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
    }
    
    @PostMapping("/{flightId}/buy")
    public ResponseEntity<TicketSaveResponse> buyTicket(@RequestBody TicketSaveRequest ticketSaveRequest, @PathVariable Long flightId) {
        TicketSaveResponse response = ticketService.buyTicket(ticketSaveRequest, flightId);
        return ResponseEntity.ok(response);
    }

    
    @DeleteMapping("/{ticketNumber}/cancel")
    public void deleteTicketByNumber(@PathVariable String ticketNumber) {
        ticketService.cancelTicket(ticketNumber);
    }
    
   
}

