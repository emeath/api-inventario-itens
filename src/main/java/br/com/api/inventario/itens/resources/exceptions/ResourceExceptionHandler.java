package br.com.api.inventario.itens.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.inventario.itens.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(
			ResourceNotFoundException e,
			HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setError("Recurso não encontrado!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
