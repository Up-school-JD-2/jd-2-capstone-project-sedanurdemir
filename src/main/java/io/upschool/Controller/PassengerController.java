package io.upschool.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.Service.PassengerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PassengerController {

	private final PassengerService passengerService;
	@DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        passengerService.delete(id);
    }
}