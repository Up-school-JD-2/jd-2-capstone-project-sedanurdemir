package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
