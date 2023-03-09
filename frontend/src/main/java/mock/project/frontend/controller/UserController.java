package mock.project.frontend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mock.project.frontend.entities.Users;
import mock.project.frontend.response.ResponseTransfer;
import mock.project.frontend.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer checkLogin(@RequestBody Users user) {
		logger.info("Checking idenityyy.......");
		Users userData = userService.checkLogin(user);
		if(userData !=null) {
			return new ResponseTransfer("Login Successful!");
		}
		return new ResponseTransfer("Login Fail!");
	}
	
	

}
