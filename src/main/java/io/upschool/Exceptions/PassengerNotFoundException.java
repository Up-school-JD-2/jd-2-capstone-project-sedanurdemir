package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PassengerNotFoundException extends RuntimeException {

	public PassengerNotFoundException (String message) {
		super(message);
	}
}
