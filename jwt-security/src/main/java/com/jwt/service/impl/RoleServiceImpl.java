/**
 * 
 */
package com.jwt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.Role;
import com.jwt.repository.RoleRepo;
import com.jwt.servicedao.RoleDao;

/**
 * @author shans
 *
 */
@Service
public class RoleServiceImpl implements RoleDao{
	
	private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleRepo roleRepo;
	
	@Override
	public Role createNewRole(Role role) throws Exception {
		log.info("Role Service Started");
		log.info("Role Service Ended");
		return roleRepo.save(role);
	}

}
