package com.springbootapplication.SpringBootApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.Services.UsersService;

@Controller
public class IndexController {

	@Autowired
	UsersService usersService;

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profile")
	public String prueba(Model model) {
		model.addAttribute("users", usersService.findAll());
		return "profile";
	}

}
