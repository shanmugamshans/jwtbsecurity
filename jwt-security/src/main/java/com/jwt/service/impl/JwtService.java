/**
 * 
 */
package com.jwt.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.common.utils.AppUtils;
import com.jwt.common.utils.JwtUtil;
import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.entity.LoginHistory;
import com.jwt.entity.User;
import com.jwt.repository.LoginHistoryRepo;
import com.jwt.repository.UserRepo;

/**
 * @author shans
 *
 */
@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private UserRepo userDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private LoginHistoryRepo loginHistoryRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest,HttpServletRequest request) throws Exception {
		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();
		LoginHistory history = new LoginHistory();
		try {
			authenticate(email, password);
		} catch (Exception e) {
			return new JwtResponse(null, null, AppUtils.FAILED);
		}
		
		final UserDetails details = loadUserByUsername(email);
		String newGenerateToken = jwtUtil.generateToken(details);
		
		history.setAction(AppUtils.ONLINE);
		history.setCreateBy(details.getUsername());
		history.setCreatedate(new Date());
		history.setIpAddress(request.getRemoteAddr());
		history.setUsername(details.getUsername());
		loginHistoryRepo.save(history);
		
		User user = userDao.findByEmail(email);
		return new JwtResponse(user, newGenerateToken, AppUtils.SUCCESS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findByEmail(email);
		
		if(null != user) {
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(), user.getPassword(), 
					getAuthorities(user) );
		}else {
			throw new UsernameNotFoundException("Email is not Valid");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set getAuthorities(User user) {
		Set authorities = new HashSet<>();
		
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		
		return authorities;
	}
	private void authenticate(String email, String Passowrd) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, Passowrd));
		} catch (DisabledException e) {
			throw new Exception("User is Disabed");
		} catch (Exception e) {
			throw new Exception("Bad Credentials from User");
		}
	}
	
	public void logout(HttpServletRequest request) {
		LoginHistory history = new LoginHistory();
		history.setAction(AppUtils.OFFLINE);
		history.setCreateBy(AppUtils.getUserAuthentication(request));
		history.setIpAddress(request.getRemoteAddr());
		history.setCreatedate(new Date());
		history.setUsername(AppUtils.getUserAuthentication(request));
		loginHistoryRepo.save(history);
	}

}
