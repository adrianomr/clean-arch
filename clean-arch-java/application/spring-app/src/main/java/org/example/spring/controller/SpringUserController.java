package org.example.spring.controller;

import org.example.controller.UserController;
import org.example.controller.model.UserWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpringUserController {

	private final UserController controller;

	@Autowired
	public SpringUserController(final UserController controller) {
		this.controller = controller;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public UserWeb createUser(@RequestBody final UserWeb userWeb) {
		return controller.createUser(userWeb);
	}


	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public UserWeb getUser(@PathVariable("userId") final String userId) {
		return controller.getUser(userId);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserWeb> allUsers() {
		return controller.allUsers();
	}
}