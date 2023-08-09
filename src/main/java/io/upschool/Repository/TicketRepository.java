package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.upschool.Entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	Ticket findByTicketNumber(String ticketNumber);


}
