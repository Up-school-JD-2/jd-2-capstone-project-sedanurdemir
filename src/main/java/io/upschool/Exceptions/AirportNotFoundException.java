package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException (String message) {
		super(message);
	}
}
