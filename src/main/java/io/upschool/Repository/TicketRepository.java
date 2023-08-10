package io.upschool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.Entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	Ticket findByTicketNumber(String ticketNumber);


}
