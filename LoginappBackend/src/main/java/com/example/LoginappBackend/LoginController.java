package com.example.LoginappBackend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public Map<String, String> userLogin(@RequestBody Map<String, String> data) {
		
		String username = data.get("username");
		String password = data.get("password");
		
		boolean result = loginService.validateUser(username, password);
		
		Map<String, String> response = new HashMap<String, String>();
		if(result == false) {
			response.put("Error", "Invalid Username or Password");
		}
		else {
			
			int salary = loginService.getSalary(username);
			response.put("success", "Login Successful");
			response.put("username", username);
			response.put("Salary", String.valueOf(salary));
		}
		return response;
	}
	
	@PostMapping("/register")
	public Map<String, String> registerUser(@RequestBody Map<String, String> data) {

	    String username = data.get("username");
	    String password = data.get("password");
	    int salary = Integer.parseInt(data.get("salary"));

	    Map<String, String> response = new HashMap<>();

	    // Check if username already exists
	    if (loginService.userExists(username)) {
	        response.put("Error", "Username already taken");
	        return response;
	    }

	    // Save new user
	    loginService.registerUser(username, password, salary);

	    response.put("success", "Registration Successful");
	    response.put("username", username);
	    response.put("salary", String.valueOf(salary));

	    return response;
	}

}
