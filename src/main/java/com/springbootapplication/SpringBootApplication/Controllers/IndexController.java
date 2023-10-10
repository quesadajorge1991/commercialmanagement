package com.springbootapplication.SpringBootApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;

@Controller
public class IndexController {

	@Autowired
	UsersRepository usersRepository;

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profile")
	public String prueba(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "profile";
	}

}
