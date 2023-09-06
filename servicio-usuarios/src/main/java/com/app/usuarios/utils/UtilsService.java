package com.app.usuarios.utils;


import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.usuarios.requests.LoginRequest;
import com.app.usuarios.requests.UserRequest;
import com.app.usuarios.responses.ErrorDetalleResponse;
import com.app.usuarios.responses.ErrorResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;



@Service
public class UtilsService {

	public ErrorResponse getError(String error)throws Exception{
		try {
			String[] detalleError = error.split("&&");
			int codigoError = Integer.parseInt(detalleError[1].trim());
			ErrorDetalleResponse errorDetalle = new ErrorDetalleResponse();
			errorDetalle.setCodigo(codigoError);
			errorDetalle.setDate(new Date());
			errorDetalle.setDetail(detalleError[0]);
			ErrorResponse errorResponse = new ErrorResponse(errorDetalle, obtieneHttpStatus(codigoError));
			return errorResponse;
		} catch (Exception e) {
			ErrorDetalleResponse errorDetalle = new ErrorDetalleResponse();
			errorDetalle.setCodigo(500);
			errorDetalle.setDate(new Date());
			errorDetalle.setDetail(error);
			ErrorResponse errorResponse = new ErrorResponse(errorDetalle, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
		
	};
	
	public HttpStatus obtieneHttpStatus(int codigo)throws Exception{
		try {
			if(codigo == 400) {
				return HttpStatus.BAD_REQUEST;
			} else if(codigo == 204) {
				return HttpStatus.NO_CONTENT;
			} else {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	};
	public void validateUserRequest(UserRequest userRequest)throws Exception{
		try {
			if(userRequest.getName() == null || userRequest.getName().trim().equals("")) {
				throw new Exception("Campo de usuario name invalido && 400 ");
			}
			if(userRequest.getEmail() == null || userRequest.getEmail().trim().equals("") || !validateEmail(userRequest.getEmail())) {
				throw new Exception("Campo de usuario email invalido && 400 ");
			}
			if(userRequest.getPassword() == null || userRequest.getPassword().trim().equals("") || !validatePassword(userRequest.getPassword())) {
				
				throw new Exception("Campo de usuario password invalido && 400 ");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	};
	public boolean validateEmail(String email)throws Exception{
		try {
			String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
			if (EMAIL_PATTERN.matcher(email).matches()) {
				return true;
	        }
	        else {
	        	return false;
	        }
			
		} catch (Exception e) {
			throw e;
		}
		
	};
	public boolean validatePassword(String password)throws Exception{
		try {
			
			String COMPLEX_PASSWORD_REGEX =
		            "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
		            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
		            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
		            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
		            "[A-Za-z0-9!]" +
		            "{8,12}$";
			
			Pattern PASSWORD_PATTERN =
                    Pattern.compile(COMPLEX_PASSWORD_REGEX);
			if (PASSWORD_PATTERN.matcher(password).matches()) {
				return true;
	        }
	        else {
	        	return false;
	        }
						
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	};
	
	 
	    public String obtienefechaStringSegunFormato(Date fecha, String formatoSalida)throws Exception {
	        String fechaString = "";
	        if (fecha.equals("null")) {
	            return fecha.toString();
	        } else {
	            try {
	                DateFormat formato = new SimpleDateFormat(formatoSalida);
	                fechaString = formato.format(fecha);
	                return fechaString;
	            } catch (Exception e) {
	                throw e;
	            }
	        }
	    }

	    
	    public Date obtienefechaDateSegunFormato(String fecha, String formatoEntrada)throws Exception {
	        try {
	            if(formatoEntrada.equals("dd/MM/yyyy")){
	                fecha = fecha.replace("-", "/").trim();
	            }
	            if(formatoEntrada.equals("yyyy/MM/dd")){
	                fecha = fecha.replace("-", "/").trim();
	            }
	            if(formatoEntrada.equals("dd-MM-yyyy")){
	                fecha = fecha.replace("/", "-").trim();
	            }
	            if(formatoEntrada.equals("yyyy-MM-dd")){
	                fecha = fecha.replace("/", "-").trim();
	            }
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoEntrada);
	            Date fechaActualDate = simpleDateFormat.parse(fecha);
	            return fechaActualDate;
	        } catch (Exception e) {
	            throw e;
	        }
	    }
	    

	    
	    public String obtieneFechaStringSegunZonaHoraria(Date date, String zonaHoraria, String formato)throws Exception {
	        try {
	            DateFormat format = new SimpleDateFormat(formato);
	            format.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
	            String formatted = format.format(date);
	            return formatted;
	        } catch (Exception e) {
	            throw e;
	        }
	    }

		public void validateLoginRequest(LoginRequest loginRequest) throws Exception {
			try {
				if(loginRequest.getEmail() == null || loginRequest.getEmail().trim().equals("")) {
					throw new Exception("Campo email invalido && 400 ");
				}
				if(loginRequest.getPassword() == null || loginRequest.getPassword().trim().equals("") || !validatePassword(loginRequest.getPassword())) {
					throw new Exception("formato contraseña invalido && 400 ");
				}
				
			} catch (Exception e) {
				throw e;
			}
			
		}

}
