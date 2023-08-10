package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FlightNotFoundException extends RuntimeException{

	public FlightNotFoundException (String message) {
		super(message);
	}
}
