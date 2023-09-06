package com.app.usuarios.responses;


import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private ErrorDetalleResponse error;
	private HttpStatus httpStatus;

	public ErrorResponse() {
	}
	
	public ErrorResponse(ErrorDetalleResponse errorDetalle, HttpStatus httpStatusIn) {
		this.error = errorDetalle;
		this.httpStatus = httpStatusIn;
	}

	

	public ErrorDetalleResponse getError() {
		return error;
	}

	public void setError(ErrorDetalleResponse error) {
		this.error = error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	

}
