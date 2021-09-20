/**
 * 
 */
package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.User;

/**
 * @author shans
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByEmailIgnoreCase(String email);

}
