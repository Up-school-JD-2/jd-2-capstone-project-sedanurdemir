package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BadRequestException extends RuntimeException{
	public BadRequestException (String message) {
		super(message);
	}
}
