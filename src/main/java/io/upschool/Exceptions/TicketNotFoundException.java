package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TicketNotFoundException extends RuntimeException{
	public TicketNotFoundException (String message) {
		super(message);
	}

}
