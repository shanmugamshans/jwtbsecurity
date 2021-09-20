/**
 * 
 */
package com.jwt.servicedao;

import com.jwt.dto.ResponseDto;
import com.jwt.entity.User;

/**
 * @author shans
 *
 */
@SuppressWarnings("rawtypes")
public interface UserDao {

	ResponseDto registerNewUser(User user) throws Exception;

	ResponseDto addNewUser(User user) throws Exception;

	ResponseDto getAllUser() throws Exception;

}
