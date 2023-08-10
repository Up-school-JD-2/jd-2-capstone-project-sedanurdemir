package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AirlineNotFoundException extends RuntimeException{

	public AirlineNotFoundException(String message) {
		super(message);
	}
}
