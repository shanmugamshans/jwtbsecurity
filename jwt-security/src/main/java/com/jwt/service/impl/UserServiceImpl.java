/**
 * 
 */
package com.jwt.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.common.utils.AppUtils;
import com.jwt.dto.ResponseDto;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.RoleRepo;
import com.jwt.repository.UserRepo;
import com.jwt.servicedao.UserDao;

/**
 * @author shans
 *
 */
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class UserServiceImpl implements UserDao{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public ResponseDto registerNewUser(User user) throws Exception {
		
		ResponseDto dto = new ResponseDto();
		log.info("Register New User Service Started");
		
		Set<Role> userRoles = new HashSet<>();
		User userDetails = userRepo.findByEmailIgnoreCase(user.getEmail());
		if(null == userDetails) {
			Role role = roleRepo.findByRoleName("USER");
			if(null == role) {
				role = new Role();
				role.setRoleName("USER");
				role.setRoleDescription("User Role");
			}
			userRoles.add(role);
			user.setRole(userRoles);
			user.setPassword(getEncodePassword(user.getPassword()));
			user.setStatus(AppUtils.ACTIVE);
			dto.setStatus(AppUtils.SUCCESS);
			dto.setResponseObj(userRepo.save(user));
			
		}else {
			dto.setErrMsg("Email Id Alread Exists");
			dto.setStatus(AppUtils.FAILED);
		}
		
		log.info("Register New User Service Ended");
		return dto;
	}

	@Override
	public ResponseDto addNewUser(User user) throws Exception {
		
		ResponseDto dto = new ResponseDto();
		Role role = new Role();
		Set<Role> adminRoles = new HashSet<>();
		log.info("Add New User Service Started");
		User userDetails = userRepo.findByEmailIgnoreCase(user.getEmail());
		if(null != userDetails) {
			dto.setErrMsg("Email Id Already Exists");
			dto.setStatus(AppUtils.FAILED);
		}else {
			for (Role role2 : user.getRole()) {
				role = roleRepo.findByRoleName(role2.getRoleName());
			}
			adminRoles.add(role);
			user.setRole(adminRoles);
			user.setPassword(getEncodePassword(user.getPassword()));
			userRepo.save(user);
			dto.setStatus(AppUtils.SUCCESS);
		}
		
		log.info("Add New User Service Ended");
		return dto;
	}
	
	public String getEncodePassword(String Password) {
		return passwordEncoder.encode(Password);
	}

	@Override
	public ResponseDto getAllUser() throws Exception {
		ResponseDto dto = new ResponseDto();
		log.info("Get All User Service Started");
		dto.setResponseDto(userRepo.findAll());
		dto.setStatus(AppUtils.SUCCESS);
		log.info("Get All User Service Ended");
		
		return dto;
	}

}
