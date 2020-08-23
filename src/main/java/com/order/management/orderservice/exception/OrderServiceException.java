package com.order.management.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderServiceException {
	
	@ExceptionHandler(value = OrderNotfoundException.class)
	   public ResponseEntity<Object> exception(OrderNotfoundException exception) {
	      return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
	   }
	@ExceptionHandler(value = OrderNotProcessException.class)
	   public ResponseEntity<Object> exception(OrderNotProcessException exception) {
	      return new ResponseEntity<>("Order Not Able to process this time", HttpStatus.BAD_REQUEST);
	   }
	@ExceptionHandler(value = OrderQuantityNotPresentException.class)
	   public ResponseEntity<Object> exception(OrderQuantityNotPresentException exception) {
	      return new ResponseEntity<>("Order Quantity not present",HttpStatus.NOT_FOUND);
	   }

}
