/**
 * 
 */
package com.jwt.servicedao;

import com.jwt.entity.Role;

/**
 * @author shans
 *
 */
public interface RoleDao {
	
	Role createNewRole(Role role) throws Exception;

}
