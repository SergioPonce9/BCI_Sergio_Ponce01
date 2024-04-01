package cl.bci.application.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}

}
