package com.excilys.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.excilys.dto.UserDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.UserMapper;
import com.excilys.model.User;
import com.excilys.service.UserService;

@Controller
//@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

	@PostMapping("/createAccount")
	public String postCreate(@ModelAttribute("user") UserDTO userDto, 
			BindingResult result, Model model, Locale locale) {
		try {
			User user = UserMapper.userDTOToUser(userDto);
			userService.createUser(user);
			model.addAttribute("created", true);
			//return "redirect:dashboard";
			return "redirect:computers";
		} catch (ValidatorException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			return "create";
		}
	}

	@GetMapping("/create")
	public String getCreate(Model model) {
		model.addAttribute("user", new UserDTO());
		return "create";
	}

}
