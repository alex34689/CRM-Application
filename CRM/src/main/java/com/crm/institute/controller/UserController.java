package com.crm.institute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.institute.enttity.UserF;
import com.crm.institute.repository.RoleRepository;
import com.crm.institute.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new UserF());
		model.addAttribute("userList", userService.getAllUsers() );
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("listTab", "active");
		return "user-form/user-view";				
	}
	
}
