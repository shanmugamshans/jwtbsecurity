/**
 * 
 */
package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.LoginHistory;

/**
 * @author shans
 *
 */
@Repository
public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Long>{

}
