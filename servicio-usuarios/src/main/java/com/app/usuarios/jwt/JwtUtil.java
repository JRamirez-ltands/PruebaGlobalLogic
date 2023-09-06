package com.app.usuarios.jwt;


import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


@PropertySource("classpath:config.properties")
@Service
public class JwtUtil {
	
	@Value("${token.secret}")
	private String tokenSecret;
	
	@Value("${token.time.expired}")
	private Integer tokenTimeExpired;

	
	 
	 public String generaTokens() throws Exception {
	        try {
	            long timeIni = System.currentTimeMillis();
	            long timeExp = timeIni + tokenTimeExpired;

	            Algorithm algorithmHS = Algorithm.HMAC256(tokenSecret);

	            String jwt = JWT.create()
	                    .withIssuer("auth0")
	                    .withIssuedAt(new Date(timeIni))
	                    .withExpiresAt(new Date(timeExp))
	                    .sign(algorithmHS);

	            return jwt;
	        } catch (Exception e){
	            throw e;
	        }

	    }

	 public boolean validaTokens(String token) throws Exception {
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
	            JWTVerifier verifier = JWT.require(algorithm)
	                    .withIssuer("auth0")
	                    .build(); 
	            DecodedJWT jwt = verifier.verify(token);
	            return true;
	        }catch (Exception e){
	            return false;
	        }
	    }

	    
	    public boolean validaIngresoToken(HttpServletRequest request)throws Exception{
	        try{
	            String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
	            if(!jwtToken.equals("") && !jwtToken.isEmpty() && jwtToken.length() != 0){
	                return true;
	            } else {
	                return false;
	            }
	        }catch (Exception e){
	            return false;
	        }
	    }

}
