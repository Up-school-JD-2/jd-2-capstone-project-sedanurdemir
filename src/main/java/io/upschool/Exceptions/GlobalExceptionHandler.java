package io.upschool.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(AirlineNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAirlineNotFoundException(AirlineNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleTicketNotFoundException(TicketNotFoundException ex){
    	ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleAirportNotFoundException(AirportNotFoundException ex){
    	ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleFlightNotFoundException(FlightNotFoundException ex){
    	ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
