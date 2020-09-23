package com.ecommerce.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Illegal Order id")
public class IllegalOrderException extends RuntimeException{

}
