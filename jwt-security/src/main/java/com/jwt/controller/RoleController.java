/**
 * 
 */
package com.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.Role;
import com.jwt.servicedao.RoleDao;

/**
 * @author shans
 *
 */
@RestController
public class RoleController {
	
	@Autowired
	private RoleDao roleDao;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@PostMapping({"/createnewrole"})
	public Role createNewRole(@RequestBody Role role) {
		try {
			log.info("Create New Role Controller Started");
			role = roleDao.createNewRole(role);
			log.info("Create New Role Controller Ended");
		} catch (Exception e) {
			log.error("Error: "+e.getMessage());
		}
		return role;
		
	}

}
