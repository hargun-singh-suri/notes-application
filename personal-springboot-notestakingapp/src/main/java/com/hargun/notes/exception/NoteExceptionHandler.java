package com.hargun.notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoteExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<NoteErrorResponse> handleException(Exception exc) {

		// Create Data for NoteErrorResponse
		NoteErrorResponse error = new NoteErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(System.currentTimeMillis());

		// Return the instance of that response
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<NoteErrorResponse> handleNoteNotFound(NoteNotFoundException exc) {

		// Create Data for NoteErrorResponse
		NoteErrorResponse error = new NoteErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(System.currentTimeMillis());

		// Return the instance of that response
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

}
