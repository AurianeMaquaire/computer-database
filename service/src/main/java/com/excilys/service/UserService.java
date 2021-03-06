package com.excilys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.excilys.dao.UserDAO;
import com.excilys.dto.UserDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.UserMapper;
import com.excilys.model.User;
import com.excilys.validator.UserValidator;

@Service
public class UserService {

	@Autowired 
	UserDAO userDao;
	
	public UserService() {
		super();
	}
	
	public Optional<UserDTO> findUser(String name) {
		Optional<User> user = userDao.find(name);
		Optional<UserDTO> userDto = Optional.empty();
		if (user.isPresent()) {
			userDto = Optional.of(UserMapper.userToUserDTO(user.get()));
		}
		return userDto;
	}
	
	public void createUser(User user) throws ValidatorException {
		
		Long id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordCoded = encoder.encode(password);
		
		User newUser = new User(id, username, passwordCoded, 1);
		
		UserValidator.verify(newUser);
		if (findUser(username).isPresent()) {
			throw new ValidatorException("exceptionUsername");
		}
		userDao.create(newUser);
		userDao.createUserRole(newUser);
	}
	
	
}
