/**
 * 
 */
package com.jwt.entity;

/**
 * @author shans
 *
 */
public class JwtResponse {
	
	private User user;
	private String jwtToken;
	private String status;
	
	public JwtResponse(User user, String jwtToken, String status) {
		this.user = user;
		this.jwtToken = jwtToken;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
