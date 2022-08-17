package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserAuth;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin")
public class AdminsController {

	private UserService userService;
	private RoleService roleService;

	public AdminsController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping()
	public String printTable(Model model) {
		model.addAttribute("usertable", userService.getAllUsersFromDatabase());
		return "admin/user_table";
	}

	@GetMapping(value = "/new")
	public String createUser(Model model) {
		model.addAttribute("roleList", roleService.getAllRolesFromDatabase());
		model.addAttribute("user", new UserAuth());
		return "admin/create";
	}

	@PostMapping("/new")
	public String create(@ModelAttribute("user") UserAuth user, @RequestParam("listRoles") ArrayList<Long> roles){
		user.addRoles(roleService.findByIdRoles(roles));
		userService.addUserToDatabase(user);
		return "redirect:/admin";
	}

	@GetMapping("/{id}/edit")
	public String updateUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getUserByIdFromDatabase(id));
		model.addAttribute("roleList", roleService.getAllRolesFromDatabase());
		return "admin/edit";
	}

	@PatchMapping("/{id}/patch")
	public String update(@ModelAttribute("user") UserAuth user, @RequestParam("listRoles") ArrayList<Long> roles) {
		user.addRoles(roleService.findByIdRoles(roles));
		userService.updateUserInDatabase(user);
		return "redirect:/admin";
	}

	@DeleteMapping("/{id}/delete/user")
	public String delete(@PathVariable("id") int id) {
		userService.removeUserByIdFromDatabase(id);
		return "redirect:/admin";
	}
}