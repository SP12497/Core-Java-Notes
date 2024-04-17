package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/index")
	public String dashboard() {
		System.out.println("Thymeleaf");
		return "userDashboard";
	}
}
