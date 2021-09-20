/**
 * 
 */
package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.Role;

/**
 * @author shans
 *
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

	Role findByRoleName(String string);

}
