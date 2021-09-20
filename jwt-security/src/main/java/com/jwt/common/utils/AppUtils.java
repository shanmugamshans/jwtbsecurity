/**
 * 
 */
package com.jwt.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shans
 *
 */
public class AppUtils {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String ACTIVE = "ACTIVE";
	public static final String ONLINE = "ONLINE";
	public static final String OFFLINE = "OFFLINE";
	
	public static String getUserAuthentication(HttpServletRequest request) {
		JwtUtil jwtUtil = new JwtUtil();
		String authentication = request.getHeader("Authorization");
		authentication.substring(7);
		authentication = jwtUtil.getUserNameFromToken(authentication.substring(7));
		return authentication;
	}

}
