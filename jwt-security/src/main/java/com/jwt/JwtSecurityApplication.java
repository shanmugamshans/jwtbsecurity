package com.jwt;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwt.common.utils.AppUtils;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.RoleRepo;
import com.jwt.repository.UserRepo;

@SpringBootApplication
public class JwtSecurityApplication {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(JwtSecurityApplication.class);
	

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}
	

	@PostConstruct
	private void createAdmin() {
		
		User existUser = userRepo.findByEmailIgnoreCase("admin123@gmail.com");
		if(null==existUser)
			admin();
	}
	
	private void admin() {
		log.info("Register New Admin Service Started");
		
		Set<Role> adminRoles = new HashSet<>();
		Role role = roleRepo.findByRoleName("ADMIN");
		if(null== role) {
			role = new Role();
			role.setRoleName("ADMIN");
			role.setRoleDescription("Admin Can Access Everything");
		}
		adminRoles.add(role);
		User user = new User();
		user.setRole(adminRoles);
		user.setPassword(getEncodePassword("admin123"));
		user.setEmail("admin123@gmail.com");
		user.setFirstName("shans");
		user.setLastName("v");
		user.setStatus(AppUtils.ACTIVE);
		
		log.info("Register New Admin Service Ended");
		
		userRepo.save(user);
	}
	public String getEncodePassword(String Password) {
		return passwordEncoder.encode(Password);
	}
}
