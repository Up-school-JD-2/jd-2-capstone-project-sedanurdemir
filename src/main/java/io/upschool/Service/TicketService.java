package io.upschool.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.upschool.DTO.TicketSaveRequest;
import io.upschool.DTO.TicketSaveResponse;
import io.upschool.DTO.TicketUpdateRequest;
import io.upschool.Entity.Flight;
import io.upschool.Entity.Passenger;
import io.upschool.Entity.Ticket;
import io.upschool.Exceptions.FlightNotFoundException;
import io.upschool.Exceptions.TicketNotFoundException;
import io.upschool.Repository.FlightRepository;
import io.upschool.Repository.PassengerRepository;
import io.upschool.Repository.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
	
	private final TicketRepository ticketRepository;
	private final FlightService flightService;
	private final FlightRepository flightRepository;
	private final PassengerRepository passengerRepository;
	
	public TicketSaveResponse save(TicketSaveRequest ticketSaveRequest) {
        Flight flight = flightService.findFlightById(ticketSaveRequest.getFlightId());

        if (flight == null) {
        	throw new FlightNotFoundException("Flight not found with id: " + ticketSaveRequest.getFlightId());
        }

        Ticket newTicket = Ticket.builder()
                                .ticketNumber(ticketSaveRequest.getTicketNumber())
                                .flight(flight)
                                .creditCard(maskCreditCard(ticketSaveRequest.getCreditCard()))
                                .isCancelled(false)
                                .build();

        Ticket savedTicket = ticketRepository.save(newTicket);

        return TicketSaveResponse.builder()
                                 .ticketId(savedTicket.getId())
                                 .ticketNumber(savedTicket.getTicketNumber())
                                 .flightId(savedTicket.getFlight().getId())
                                 .CreditCard(savedTicket.getCreditCard())
                                 .isCancelled(savedTicket.isCancelled())
                                 .build();
    }

	 public List<TicketSaveResponse> getAllTickets() {
	        List<Ticket> tickets = ticketRepository.findAll();
	        List<TicketSaveResponse> responses = new ArrayList<>();
	        for (Ticket ticket : tickets) {
	            TicketSaveResponse response = ticket.toTicketSaveResponse();
	            responses.add(response);
	        }
	        return responses;
	    }

    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found " +id));
    }

    public Ticket findTicketByNumber(String ticketNumber) {
    	return ticketRepository.findByTicketNumber(ticketNumber);
    }
    public TicketSaveResponse update(TicketUpdateRequest request) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(request.getId());
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setTicketNumber(request.getTicketNumber());
            ticket.setCreditCard(maskCreditCard(request.getMaskedCreditCard()));
            ticket = ticketRepository.save(ticket);

            return TicketSaveResponse.builder()
                                     .ticketId(ticket.getId())
                                     .flightId(ticket.getFlight().getId())
                                     .ticketNumber(ticket.getTicketNumber())
                                     .CreditCard(ticket.getCreditCard())
                                     .isCancelled(ticket.isCancelled())
                                     .build();
        }
        throw new TicketNotFoundException("Ticket not found with id: " + request.getId());
    }

    public void delete(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
        } else {
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }
    }

    

    // Kredi kartı bilgisini maskeleyen yardımcı metod
    private String maskCreditCard(String creditCardNumber) {
    	
    	   StringBuilder maskedCreditCard = new StringBuilder();
   
    	// Sadece rakamları içeren bir kredi kartı numarası oluştur
        String cleanedNumber = creditCardNumber.replaceAll("[^0-9]", "");

        int visibleLength = 4; // Son 4 hane görünür, geri kalanları maskeli olacak
        int cardNumberLength = cleanedNumber.length();

        // Maskelenmiş kredi kartı numarasını oluştur
        
        for (int i = 0; i < cardNumberLength - visibleLength; i++) {
        	if (i < 6 || i >= cardNumberLength - 4) {
                maskedCreditCard.append(cleanedNumber.charAt(i));
            } else {
                maskedCreditCard.append("*");
            }
            
            if ((i + 1) % 4 == 0) { // 4 karakterden sonra bir boşluk ekle
                maskedCreditCard.append(" ");
            }
        }
        
        // Son 4 hane ekle
        maskedCreditCard.append(cleanedNumber.substring(cardNumberLength - visibleLength));
         
        return maskedCreditCard.toString();
    }
    
    
    public TicketSaveResponse buyTicket(TicketSaveRequest ticketSaveRequest, Long flightId) {
        // Uçuşun var olduğunu kontrol edelim
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + flightId));

        // Uçuşun toplam koltuk sayısını ve satılan koltuk sayısını kontrol edelim
        int totalSeats = flight.getTotalSeats();
        int soldSeats = flight.getSoldSeats();
        if (soldSeats >= totalSeats) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats for the flight with id: " + flightId);
        }

        // Yeni Passenger nesnesi oluşturalım
        Passenger passenger=Passenger.builder()
                .firstName(ticketSaveRequest.getPassengerFirstName())
                .lastName(ticketSaveRequest.getPassengerLastName())
                .build();
        passenger=passengerRepository.save(passenger);

        // Kredi kartı numarasını maskeleyelim
        
        if (ticketSaveRequest.getCreditCard() == null) {
            // creditCardNumber null ise maskeleme işlemi yapılamaz, hata döndür
            throw new IllegalArgumentException("Credit card number cannot be null.");
        }
        String maskedCreditCard = maskCreditCard(ticketSaveRequest.getCreditCard());

        // Yeni bilet oluşturalım
        Ticket newTicket = Ticket.builder()
                .flight(flight)
                .creditCard(maskedCreditCard)
                .passenger(passenger)
                .saleDate(LocalDateTime.now()) // Satış tarihini kaydedelim
                .build();
       

        // Biletin iptal edilip edilmediğini kontrol edelim
        boolean isCancelled = ticketSaveRequest.isCancelled();
        if (isCancelled) {
            newTicket.setCancelled(true);
            newTicket.setCancellationDate(LocalDateTime.now());
        }

        newTicket.generateTicketNumber();
        // Biletin satılan koltuk sayısını arttıralım ve uçuşu güncelleyelim
        flight.setSoldSeats(soldSeats + 1);
        flightRepository.save(flight);

        // Bileti kaydedelim
        Ticket savedTicket = ticketRepository.save(newTicket);

        // Response olarak uygun TicketSaveResponse nesnesini döndürelim
        return TicketSaveResponse.builder()
                .ticketId(savedTicket.getId())
                .CreditCard(savedTicket.getCreditCard())
                .ticketNumber(savedTicket.getTicketNumber())
                .passengerFirtsName(savedTicket.getPassenger().getFirstName())
                .passengerLastName(savedTicket.getPassenger().getLastName())
                .flightId(savedTicket.getFlight().getId())
                .isCancelled(savedTicket.isCancelled())
                .cancellationDate(savedTicket.getCancellationDate())
                .saleDate(savedTicket.getSaleDate())
                .build();
    }
    
    public String cancelTicket(String ticketNumber) {
        // Veritabanında bilet kaydını bilet numarasıyla bulun
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);

        // Bilet kaydını bulamazsa hata fırlatın
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found with ticket number: " + ticketNumber);
        }

        // Bilet zaten iptal edilmişse hata fırlatın
        if (ticket.isCancelled()) {
            throw new IllegalStateException("Ticket with ticket number " + ticketNumber + " is already cancelled.");
        }

        // Uçuşun toplam satılan koltuk sayısını güncelle
        Flight flight = ticket.getFlight();
        flight.setSoldSeats(flight.getSoldSeats() - 1);
        flightRepository.save(flight);

        // Biletin iptal tarihini ve durumunu güncelle
        ticket.setCancelled(true);
        ticket.setCancellationDate(LocalDateTime.now());

        ticketRepository.save(ticket);
       
        return ticketNumber+"başarılı bir şekilde iptal edilmiştir.";
    }

    
   
}
