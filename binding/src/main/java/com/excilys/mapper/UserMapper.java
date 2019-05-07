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
	
	public static User userDTOToUser(UserDTO userDto) {
		Long id = userDto.getId();
		String username = userDto.getUsername();
		String password = userDto.getPassword();
		int enabled = userDto.getEnabled();
		
		return new User(id, username, password, enabled);
	}
	
}
