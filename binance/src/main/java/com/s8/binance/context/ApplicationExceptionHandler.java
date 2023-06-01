package com.s8.binance.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.s8.binance.model.request.MessageDto;
import com.s8.binance.security.util.Operations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.s8.binance.security.util.Message;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Message> runtimeException(RuntimeException runtimeException) {
		Message message = Message.builder().message(runtimeException.getMessage()).build();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<MessageDto> throwNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MessageDto(HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	@ExceptionHandler(AttributeException.class)
	public ResponseEntity<MessageDto> throwAttributtException(AttributeException ex) {
		return ResponseEntity.badRequest()
				.body(new MessageDto(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MessageDto> throwGeneralException(Exception ex) {
		return ResponseEntity.internalServerError()
				.body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageDto> validationException(MethodArgumentNotValidException e){
		List<String> messages = new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach((err) -> {
			messages.add(err.getDefaultMessage());
		});
		return ResponseEntity.badRequest()
				.body(new MessageDto(HttpStatus.BAD_REQUEST, Operations.trimBrackets(messages.toString())));
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<MessageDto> badCredentialsException(BadCredentialsException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new MessageDto(HttpStatus.NOT_FOUND, "bad credentials"));
	}
}
