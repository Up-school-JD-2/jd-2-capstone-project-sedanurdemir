package io.upschool.Exceptions;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RouteNotFoundException extends RuntimeException{

	public RouteNotFoundException (String message) {
		super(message);
	}
}
