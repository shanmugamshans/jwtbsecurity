/**
 * 
 */
package com.jwt.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author shans
 *
 */
@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "scratch_to_devlop";
	private static final int TOKEN_VALIDITY = 3600 * 5;
	
	public String getUserNameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	/*higher order function*/
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResover) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResover.apply(claims);
		
	}
	
	/*Secret key used for application*/
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token, UserDetails details) {
		String userName = getUserNameFromToken(token);
		return (userName.equals(details.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		final Date expirationDate = getExpDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	private Date getExpDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
}
