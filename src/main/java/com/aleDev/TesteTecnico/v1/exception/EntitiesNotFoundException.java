package com.aleDev.TesteTecnico.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntitiesNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntitiesNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	

}
