package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.dto.UserDTO;
import com.excilys.model.User;

@Component
public class UserMapper {

	public static UserDTO userToUserDTO(User user) {
		long id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();
		int enabled = user.getEnabled();
		
		return new UserDTO(id, username, password, enabled);
	}
	
	
}
