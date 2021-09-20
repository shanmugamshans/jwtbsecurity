/**
 * 
 */
package com.jwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dto.ResponseDto;
import com.jwt.entity.User;
import com.jwt.service.impl.JwtService;
import com.jwt.servicedao.UserDao;

/**
 * @author shans
 *
 */
@CrossOrigin
@RestController
@SuppressWarnings("rawtypes")
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private JwtService jwtService;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@PostMapping({"/addNewUser"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity addNewUser(@RequestBody User user) {
		ResponseDto dto = new ResponseDto<>();
		try {
			log.info("Add New User Controller Started");
			dto = userDao.addNewUser(user);
			log.info("Add New User Controller Ended");
		} catch (Exception e) {
			log.error("Error: "+e.getMessage());
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
		
	}
	
	@PostMapping({"/register"})
	public ResponseEntity<ResponseDto> register(@RequestBody User user) throws Exception{
		ResponseDto responseDto = new ResponseDto<>();
		responseDto = userDao.registerNewUser(user);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@GetMapping({"/getAllUser"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto> getAllUser() throws Exception{
		ResponseDto responseDto = new ResponseDto<>();
		responseDto = userDao.getAllUser();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@GetMapping({"/signout"})
	public void logout(HttpServletRequest request) throws Exception{
		jwtService.logout(request);
	}

}
