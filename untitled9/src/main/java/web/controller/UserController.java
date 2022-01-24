package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

// фильтры
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "")
	public String printWelcome(ModelMap model) {
		model.addAttribute("users", userService.getUsers());
		return "users";
	}
	
	@PostMapping("/{id}/delete")
	public String usersDelete(@PathVariable(value = "id") Long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
	
	@PostMapping("/createUser")
	public String userAdd(String firstName, String lastName) {
		userService.createUser(new User(firstName, lastName));
		return "redirect:/users";
	}
	@PostMapping ("/updateUsers")
	public String updateDepartament(Long id, String firstName,String lastName){
		User user = userService.getUserById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		userService.updateUser(user);
		return "redirect:/users";
	}
}