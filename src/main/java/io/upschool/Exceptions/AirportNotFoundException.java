package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException (String message) {
		super(message);
	}
}
